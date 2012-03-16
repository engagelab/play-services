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
        "click #sim-separator":"handleBarClicked",
        "click #sim-tab1":"showTab1",
        "click #sim-tab2":"showTab2"
    },

    handleBarClicked:function (event) {
        event.preventDefault();
        if($('#div-tab1').hasClass('use-sidebar')) {
        	$('#div-tab1').removeClass('use-sidebar');
		}
		else {
			$('#div-tab1').addClass('use-sidebar');
		}
    },
    
    showTab1:function(event) {
    	event.preventDefault();
    	$('#div-tab2').hide();
    	$('#sim-tab2').removeClass('sim-active');
    	$('#sim-tab1').addClass('sim-active');
    	$('#div-tab1').show();
    	
    },
    
    showTab2:function(event) {
    	event.preventDefault();
    	$('#div-tab1').hide();
    	$('#sim-tab1').removeClass('sim-active');
    	$('#sim-tab2').addClass('sim-active');
    	$('#div-tab2').show();
    }
});