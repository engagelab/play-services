window.PostitListView = Backbone.View.extend({

	initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		$(this.el).html('');
		_.each(this.model.models, function(postit) {
			$(this.el).append(new PostitListItemView({
				model : postit
			}).render().el);
		}, this);
		return this;
	}
});

window.PostitListItemView = Backbone.View.extend({
	tagName : "div",
	className : "postick",

	initialize : function() {
		this.template = _.template(tpl.get('postit-item'));
		this.model.bind("change", this.render, this);
		this.model.bind("destroy", this.close, this);
	},
	
	updatePosition : function(event) {
		var pleft = this.el.style.left;
		this.model.attributes.xpos = pleft.substring(0, pleft.length-2);
		var ptop = this.el.style.top;
		this.model.attributes.ypos = ptop.substring(0, ptop.length-2);
		this.model.attributes.content = $('div#'+this.model.id+' .editable').text();
		this.model.save();
	},
	
	events : {
		"click .deletePostit" : "deletePostit",
		"dragstop .toolbar" : "updatePosition"
	},

	render : function(eventName) {
		this.model.id = this.model.attributes._id;
		$(this.el).attr('style', 'left:' + this.model.attributes.xpos + 'px;top:' + this.model.attributes.ypos + 'px')
		$(this.el).attr('id', this.model.id)
		$(this.el).html(this.template(this.model.toJSON()))
		$(this.el).draggable({
			handle : '.toolbar',
		});
		return this;
	},
	deletePostit : function(event) {
		this.model.destroy({
			success : function() {
				alert('Postit deleted successfully');
			}
		});
		return false;
	}
});
