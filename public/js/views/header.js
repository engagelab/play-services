//handles header view and related events
window.HeaderView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('header_tpl'));
    },

    render:function (eventName) {
        $(this.el).html(this.template({ name: this.options }));
        return this;
    },

    events:{
    	"click #navToResources": "displayResources",
    	"click #headerTitle" : "goHome"
    },
    
    displayResources:function(event) {
    	app.navigate("/#/resources");
    },
    
    goHome:function(event) {
    	app.navigate("/#/groups");
    }
});