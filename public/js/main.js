Backbone.View.prototype.close = function () {
    console.log('Closing view ' + this);
    if (this.beforeClose) {
        this.beforeClose();
    }
    this.remove();
    this.unbind();
};

var AppRouter = Backbone.Router.extend({

	initialize:function () {
        $('#header').html(new HeaderView().render().el);
        var appSelf = this;
    },

    routes:{
        "":"list",
        "/simulations" : "viewSimulations"
    },

	//executed when root path called
    list:function () {
    	app.initPitBoard();
    	app.showView('#scenes', new ScenePickerView(), 'menu');
    	this.before();
    },
    
    //create new postit
    newPostit:function () {
       app.postitList.create({wait : true, user: "fahied", sceneId: app.currentViewId });
    },
    
    //switch to simulations view
    viewSimulations:function () {
    	app.initPitBoard();
    	app.simulation = new SimulationsView();
        app.showView('#scenes', app.simulation, 'simu1');
        this.before();
    },

	//view switcher function
    showView:function (selector, view, viewid) {
        if (this.currentView) {
            this.currentView.close();
        }
        $(selector).html(view.render().el);
        this.currentView = view;
        this.currentViewId = viewid;
        return view;
    },
    
    initPitBoard:function() {
    	$('#board').html('');
        app.postitList = null;
    },

	//preloader of collections
    before:function (callback) {
        if (this.postitList) {
            if (callback) callback();
        } else {
            this.postitList = new PostitCollection();
            this.postitList.fetch({data: { user: "fahied", sceneId: app.currentViewId }, success:function () {
                $('#board').html(new PostitListView({model:app.postitList}).render().el);
                if (callback) callback();
            }});
        }
    }

});

//loading html templates
tpl.loadTemplates(['header', 'postit-item', 'scene-picker', 'simulations'], function () {
    app = new AppRouter();
    Backbone.history.start();
});