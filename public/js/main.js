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
        //$('#header').html(new HeaderView().render().el);
    },

    routes:{
        "":"list"
    },

    list:function () {
        this.before();
    },
    
    newPostit:function () {
        this.before(function () {
        	app.postitList.create({wait : true, user: "jeremyt", sceneId: "simulation1" });
        });
    },

    showView:function (selector, view) {
        if (this.currentView)
            this.currentView.close();
        $(selector).html(view.render().el);
        this.currentView = view;
        return view;
    },

    before:function (callback) {
        if (this.postitList) {
            if (callback) callback();
        } else {
            this.postitList = new PostitCollection();
            this.postitList.fetch({success:function () {
                $('#board').html(new PostitListView({model:app.postitList}).render().el);
                if (callback) callback();
            }});
        }
    }

});

tpl.loadTemplates(['header', 'postit-item'], function () {
    app = new AppRouter();
    Backbone.history.start();
});