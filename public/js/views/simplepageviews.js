//handles resources view and related events
window.ResourceView = Backbone.View.extend({
	tagName : "div",
	className : "res",
	
    render : function(eventName) {
		$(this.el).html(tpl.get('resource_tpl'));
		return this;
	}
});

//handles group resources view and related events
window.GrpResourceView = Backbone.View.extend({
	tagName : "div",
	className : "grpres",
	
    render : function(eventName) {
		$(this.el).html(tpl.get('grpresource_tpl'));
		return this;
	}
});

//handles simulation view and related events
window.SimulationView = Backbone.View.extend({
	tagName : "div",
	className : "simu",
	
    render : function(eventName) {
		$(this.el).html(tpl.get('simulations_tpl'));
		return this;
	}
});
