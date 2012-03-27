window.ContentView = Backbone.View.extend({
	tagName : "div",
	className : "contentsContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		$(this.el).html('<div class="comGrpName">'+app.selectedGroupName+'</div>');
		_.each(this.model.models, function(content) {
			$(this.el).append(new ContentItemView({model : content}).render().el);
		}, this);
		return this;
	}
});

window.ContentItemView = Backbone.View.extend({
	
	className : 'content-ui',
	
	initialize : function() {
		this.templateComm = _.template(tpl.get('comment_tpl'));
	},
	
	events : {
		//"dragstop .toolbar" : "updatePosition"
	},

	render : function(eventName) {
		//$(this.el).html(this.templateComm(this.model.toJSON()));
		$(this.el).html('');
		_.each(this.model.attributes.comments, function(comment) {
			$(this.el).append(new CommentContentItemView({model : comment}).render().el);
		}, this);
		_.each(this.model.attributes.ytVideos, function(video) {
			$(this.el).append(new VideoContentItemView({model : video}).render().el);
		}, this);

		return this;
	}
});

window.CommentContentItemView = Backbone.View.extend({
	
	className : 'comment-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('comment_tpl'));
	},
	
	events : {
		"dragstop" : "updatePosition"
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model));
		$(this.el).draggable({
			//handle : '.toolbar',
			stack: "div",
			containment: 'content-ui'
		});
		return this;
	}
});

window.VideoContentItemView = Backbone.View.extend({
	
	className : 'ytvideo-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('ytvideo_tpl'));
	},
	
	events : {
		//"dragstop .toolbar" : "updatePosition"
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model));
		$(this.el).draggable({
			//handle : '.toolbar',
			stack: "div",
			containment: 'content-ui'
		});
		return this;
	}
});
