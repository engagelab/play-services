window.ActPickerView = Backbone.View.extend({
	tagName : "div",
	className : "actContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		$(this.el).html('');
		_.each(this.model.models, function(act) {
			$(this.el).append(new ActListItemView({model : act}).render().el);
		}, this);
		return this;
	}
});

window.ActListItemView = Backbone.View.extend({
	
	className : 'act-picker-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('act-picker'));
	},
	
	events : {
		"click .act-label" : "pickAct",
	},

	render : function(eventName) {
		$(this.el).attr('id', this.model.id)
		$(this.el).html(this.template(this.model.toJSON()))
		return this;
	},
	
	pickAct : function(event) {
		app.navigate("/#/scenes/"+this.model.id, true);
	}
});
