// view that handles the comments tabs
window.PostitView = Backbone.View.extend({
	tagName : "div",
	className : "postitsContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		_.each(this.model.models, function(comment) {
			/* test whether the postits should be displayed ordered or fuzzy */
			if(this.options.activityMode == 2) {
				$(this.el).append(new PostitItemView({ model : comment, activityMode:this.options.activityMode }).render().el);
			}
			else if(this.options.activityMode == 1) {
				$('#activityContainer').append(new PostitItemView({ model : comment, activityMode:this.options.activityMode }).render().el);
			}
		}, this);
		return this;
	}
});

window.PostitItemView = Backbone.View.extend({
	
	initialize : function() {
		_.bindAll(this, "createPostitComments");
		this.template = _.template(tpl.get('postit_tpl'));
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
		"keypress .commentInput" : "handleKeyPress",
		"click .commentInput" : "handleOnfocus",
		"focusout .commentInput" : "handleOnfocusout"
	},
	
	handleKeyPress:function(event) {
		var key = event.keyCode;
		if(key==13 && event.currentTarget.value!='') {
			var pc = new PostitComment();
			pc.attributes.content = event.currentTarget.value;
			pc.attributes.postit_id = this.model.id;
			pc.save({ wait: true });
			this.commentModel.add(pc);
		}
	},
	
	handleOnfocus:function(event) {
		event.currentTarget.value = '';
	},
	
	handleOnfocusout:function(event) {
		event.currentTarget.value = 'Write a comment...';
	},
	
	createPostitComments: function(m, response) {
		this.commentModel = m;
		this.commentModel.bind("add", this.refreshPostitComments, this);
		_.each(this.commentModel.models, function(comment) {
			$('#postitComment-'+this.model.id).append(new CommentItemView({model : comment}).render().el);
		}, this);
	},
	
	refreshPostitComments : function() {
		$('#postitComment-'+this.model.id).html('');
		this.postitCommentList = new PostitCommentCollection();
		this.postitCommentList.fetch({ data: $.param({ postit_id: this.model.id}),
			success : this.createPostitComments,
			wait: true
		});
		$('.commentInput').blur();
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		
		this.refreshPostitComments();
		
		if(this.options.activityMode == 1) {
			$(this.el).attr('style', 'left:' + this.model.attributes.wxpos + 'px;top:' + this.model.attributes.wypos + 'px');
			$(this.el).addClass('postit-ui');
			$(this.el).draggable({
				//handle : '.toolbar',
				stack: "activityContainer",
				containment: 'activityContainer'
			});
		}
		else if(this.options.activityMode == 2) {
			$(this.el).addClass('postit-ui-tl');
		}
		return this;
	}
});