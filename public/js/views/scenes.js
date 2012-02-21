window.ScenePickerView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('scene-picker'));
    },

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    },

    events:{
        "click .scene-picker-ui ":"openScene"
    },

    openScene:function (event) {
        app.navigate("/#/simulations", true);
        return false;
    }
});