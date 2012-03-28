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
		this.model.bind("change", this.render, this);
	},
	
	updatePosition : function(event) {
		/*var pleft = this.el.style.left;
		this.model.attributes.xpos = pleft.substring(0, pleft.length-2);
		var ptop = this.el.style.top;
		this.model.attributes.ypos = ptop.substring(0, ptop.length-2);
		this.model.attributes.content = $('div#'+this.model.id+' .editable').text();*/
		this.model.attributes.wxpos = 50;
		this.model.attributes.wypos = 80;
		this.model.save();
	},
	
	
	events : {
		"dragstop" : "updatePosition"
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