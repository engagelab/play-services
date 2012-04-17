window.SimulationView = Backbone.View.extend({
	tagName : "div",
	className : "simu",
	
    initialize : function() {
	},
	
	render : function(eventName) {
		$(this.el).html(tpl.get('simulations_tpl'));
		return this;
	}
});