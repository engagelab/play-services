window.ResourceView = Backbone.View.extend({
	tagName : "div",
	className : "res",
	
    initialize : function() {
	},
	
	render : function(eventName) {
		$(this.el).html(tpl.get('resource_tpl'));
		return this;
	}
});

window.GrpResourceView = Backbone.View.extend({
	tagName : "div",
	className : "grpres",
	
    initialize : function() {
	},
	
	render : function(eventName) {
		$(this.el).html(tpl.get('grpresource_tpl'));
		return this;
	}
});

