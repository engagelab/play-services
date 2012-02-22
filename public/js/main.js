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
    },

    routes:{
        "":"list",
        "/simulations" : "viewSimulations"
    },

	//executed when root path called
    list:function () {
        this.before(function () {
        	app.showView('#scenes', new ScenePickerView());
        });
    },
    
    //create new postit
    newPostit:function () {
        this.before(function () {
        	app.postitList.create({wait : true, user: "fahied", sceneId: "simulation1" });
        });
    },
    
    //switch to simulations view
    viewSimulations:function () {
    	this.before(function () {
            app.showView('#scenes', new SimulationsView());
        });
    },

	//view switcher function
    showView:function (selector, view) {
        if (this.currentView) {
            this.currentView.close();
        }
        $(selector).html(view.render().el);
        this.currentView = view;
        return view;
    },

	//preloader of collections
    before:function (callback) {
        if (this.postitList) {
            if (callback) callback();
        } else {
            this.postitList = new PostitCollection();
            this.postitList.fetch({data: { user: "fahied", sceneId: "simulation1" }, success:function () {
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