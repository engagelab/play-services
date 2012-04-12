window.PicView = Backbone.View.extend({
	tagName : "div",
	className : "picContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		_.each(this.model.models, function(content) {
			if(this.options.mmode == 2) {
				$(this.el).append(new PicItemView({model : content, mmode:this.options.mmode}).render().el);
			}
			else if(this.options.mmode == 1) {
				$('#acCont').append(new PicItemView({model : content, mmode:this.options.mmode}).render().el);
			}
		}, this);
		return this;
	}
});

window.PicItemView = Backbone.View.extend({
	
	initialize : function() {
		_.bindAll(this, "createFBPicComments");
		this.template = _.template(tpl.get('pic_tpl'));
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
			var fbpc = new FBPicComment();
			fbpc.attributes.fbcontent = event.currentTarget.value;
			fbpc.attributes.vid_id = this.model.id;
			fbpc.save({wait: true});
			this.fbmodel.add(fbpc);
		}
	},
	
	handleOnfocus:function(event) {
		event.currentTarget.value = '';
	},
	
	handleOnfocusout:function(event) {
		event.currentTarget.value = 'Write a comment...';
	},
	
	createFBPicComments: function(m, response) {
		this.fbmodel = m;
		this.fbmodel.bind("add", this.refreshFBComments, this);
		_.each(this.fbmodel.models, function(fbcomment) {
			$('#piccomms').append(new FBCommentItemView({model : fbcomment}).render().el);
		}, this);
	},
	
	refreshFBComments : function() {
		$('#piccomms').html('');
		this.fbpcList = new FBPicCommentCollection();
		this.fbpcList.fetch({ data: $.param({ pic_id: this.model.id}),
			success : this.createFBPicComments,
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
			$(this.el).addClass('pic-ui');
			$(this.el).draggable({
				//handle : '.toolbar',
				stack: "acCont",
				containment: 'acCont'
			});
		}
		else {
			$(this.el).addClass('pic-ui-tl');
		}
		return this;
	}
});