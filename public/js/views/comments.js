window.CommentView = Backbone.View.extend({
	tagName : "div",
	className : "commentsContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		_.each(this.model.models, function(comment) {
			if(this.options.mmode == 2) {
				$(this.el).append(new CommentItemView({model : comment, mmode:this.options.mmode}).render().el);
			}
			else if(this.options.mmode == 1) {
				$('#acCont').append(new CommentItemView({model : comment, mmode:this.options.mmode}).render().el);
			}
		}, this);
		return this;
	}
});

window.CommentItemView = Backbone.View.extend({
	
	initialize : function() {
		_.bindAll(this, "createFBComments");
		this.template = _.template(tpl.get('comment_tpl'));
		this.model.bind("change", this.render, this);
	},
	
	updatePosition : function(event) {
		var pleft = this.el.style.left;
		var ptop = this.el.style.top;
		this.model.attributes.wxpos = pleft.substring(0, pleft.length-2);
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
			var fbc = new FBComment();
			fbc.attributes.fbcontent = event.currentTarget.value;
			fbc.attributes.comment_id = this.model.id;
			fbc.save({wait: true});
			this.fbmodel.add(fbc);
		}
	},
	
	handleOnfocus:function(event) {
		event.currentTarget.value = '';
	},
	
	handleOnfocusout:function(event) {
		event.currentTarget.value = 'Write a comment...';
	},
	
	createFBComments: function(m, response) {
		this.fbmodel = m;
		this.fbmodel.bind("add", this.refreshFBComments, this);
		_.each(this.fbmodel.models, function(fbcomment) {
			$('#fbcomms').append(new FBCommentItemView({model : fbcomment}).render().el);
		}, this);
	},
	
	refreshFBComments : function() {
		$('#fbcomms').html('');
		this.fbcList = new FBCommentCollection();
		this.fbcList.fetch({ data: $.param({ comment_id: this.model.id}),
			success : this.createFBComments,
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
		
		this.refreshFBComments();
		
		if(this.options.mmode == 1) {
			$(this.el).addClass('comment-ui');
			$(this.el).draggable({
				//handle : '.toolbar',
				stack: "acCont",
				containment: 'acCont'
			});
		}
		else {
			$(this.el).addClass('comment-ui-tl');
		}
		return this;
	}
});

window.FBCommentItemView = Backbone.View.extend({
	className : 'fbcomment-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('fbcomment_tpl'));
	},
	
	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		return this;
	}
});	