// view that handles the activity tabs
window.ActivityView = Backbone.View.extend({
	tagName : "div",
	id : "activityContainer",
	
    initialize : function() {
    	this.template = _.template(tpl.get('activity_tpl'));
	},
	
	events : {
		"click #fuzzy":"showFuzzy",
		"click #timeline":"showTimeline"
	},
	
	showFuzzy: function(event) {
		app.navigate("/#/groupcontent/"+this.options.grpid+"/"+this.options.grpname+"/1");
	},
	
	showTimeline: function(event) {
		app.navigate("/#/groupcontent/"+this.options.grpid+"/"+this.options.grpname+"/2"); 
	},
	
	render : function(eventName) {
		$(this.el).html(this.template());
		return this;
	}
});