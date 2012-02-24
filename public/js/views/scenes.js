window.ScenePickerView = Backbone.View.extend({
	tagName : "div",
	className : "sceneContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		$(this.el).html('');
		_.each(this.model.models, function(scene) {
			$(this.el).append(new SceneListItemView({model : scene}).render().el);
		}, this);
		return this;
	}
});

window.SceneListItemView = Backbone.View.extend({
	
	className : 'scene-picker-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('scene-picker'));
	},
	
	events : {
		"click .scene-label" : "pickScene",
	},

	render : function(eventName) {
		$(this.el).attr('id', this.model.id)
		$(this.el).html(this.template(this.model.toJSON()))
		return this;
	},
	
	pickScene : function(event) {
		if(this.model.attributes.doctype == 'simulation') {
			app.navigate("/#/"+this.model.attributes.doctype+"/1", true);
		}
	}
});