window.SimulationsView = Backbone.View.extend({
	
	tagName : "div",
	className : "center-wrapper",
	
	initialize:function () {
		this.template = _.template(tpl.get('simulations'));
    },

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    },

    events:{
        "click #sim-separator":"handleBarClicked"
    },

    handleBarClicked:function (event) {
        event.preventDefault();
        if($('#main').hasClass('use-sidebar')) {
        	$('#main').removeClass('use-sidebar');
		}
		else {
			$('#main').addClass('use-sidebar');
		}
    }
});