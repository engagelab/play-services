window.CommentView = Backbone.View.extend({
	tagName : "div",
	className : "commentsContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		$(this.el).html('<div class="comGrpName">'+app.selectedGroupName+'</div>');
		_.each(this.model.models, function(comment) {
			$(this.el).append(new CommentItemView({model : comment}).render().el);
		}, this);
		return this;
	}
});

window.CommentItemView = Backbone.View.extend({
	
	className : 'comment-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('comment_tpl'));
	},
	
	events : {
		//"dragstop .toolbar" : "updatePosition"
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		$(this.el).draggable({
			//handle : '.toolbar',
			stack: "div",
			containment: 'commentsContainer'
		});
		return this;
	}
});
