window.ActivityView = Backbone.View.extend({
	tagName : "div",
	className : "activityContainer",
	id : "acCont",
	
    initialize : function() {
    	this.template = _.template(tpl.get('activity_tpl'));
	},
	
	events : {
		"click #fuzzy":"showFuzzy",
		"click #timeline":"showTimeline"
	},
	
	showFuzzy: function(event) {
		app.navigate("/#/groupcontent/1/group1/1");
	},
	
	showTimeline: function(event) {
		app.navigate("/#/groupcontent/1/group1/2"); 
	},
	
	render : function(eventName) {
		$(this.el).html(this.template());
		return this;
	}
});