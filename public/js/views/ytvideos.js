window.YTVideoView = Backbone.View.extend({
	tagName : "div",
	className : "ytvideoContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		_.each(this.model.models, function(content) {
			if(this.options.mmode == 2) {
				$(this.el).append(new YTVideoItemView({model : content, mmode:this.options.mmode}).render().el);
			}
			else if(this.options.mmode == 1) {
				$('#acCont').append(new YTVideoItemView({model : content, mmode:this.options.mmode}).render().el);
			}
		}, this);
		return this;
	}
});

window.YTVideoItemView = Backbone.View.extend({
	
	initialize : function() {
		_.bindAll(this, "createFBVideoComments");
		this.template = _.template(tpl.get('ytvideo_tpl'));
		this.model.bind("change", this.render, this);
	},
	
	updatePosition : function(event) {
		var pleft = this.el.style.left;
		var ptop = this.el.style.top;
		this.model.attributes.wxpos = pleft.substring(0, pleft.length-2);;
		this.model.attributes.wypos = ptop.substring(0, ptop.length-2);
		this.model.save();
	},
	
	
	events : {
		"dragstop" : "updatePosition",
		"keypress .newFBInput" : "handleKeyPress",
		"click .newFBInput" : "handleOnfocus",
		"focusout .newFBInput" : "handleOnfocusout"
	},
	
	handleKeyPress:function(event) {
		var key = event.keyCode;
		if(key==13 && event.currentTarget.value!='') {
			var fbvc = new FBVideoComment();
			fbvc.attributes.fbcontent = event.currentTarget.value;
			fbvc.attributes.vid_id = this.model.id;
			fbvc.save({wait: true});
			this.fbmodel.add(fbvc);
		}
	},
	
	handleOnfocus:function(event) {
		event.currentTarget.value = '';
	},
	
	handleOnfocusout:function(event) {
		event.currentTarget.value = 'Write a comment...';
	},
	
	createFBVideoComments: function(m, response) {
		this.fbmodel = m;
		this.fbmodel.bind("add", this.refreshFBComments, this);
		_.each(this.fbmodel.models, function(fbcomment) {
			$('#vidcomms').append(new FBCommentItemView({model : fbcomment}).render().el);
		}, this);
	},
	
	refreshFBComments : function() {
		$('#vidcomms').html('');
		this.fbvcList = new FBVideoCommentCollection();
		this.fbvcList.fetch({ data: $.param({ vid_id: this.model.id}),
			success : this.createFBVideoComments,
			wait: true
		});
		$('#newFBInputId').val('Write a comment...');
		$('#newFBInputId').blur();
	},

	render : function(eventName) {
		if(this.options.mmode == 1) {
			$(this.el).attr('style', 'left:' + this.model.attributes.wxpos + 'px;top:' + this.model.attributes.wypos + 'px');
		}
		
		$(this.el).html(this.template(this.model.toJSON()));
		
		if(this.options.mmode == 1) {
			$(this.el).addClass('ytvideo-ui');
			$(this.el).draggable({
				//handle : '.toolbar',
				stack: "acCont",
				containment: 'acCont'
			});
		}
		else {
			$(this.el).addClass('ytvideo-ui-tl');
		}
		return this;
	}
});