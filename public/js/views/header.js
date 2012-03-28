window.HeaderView = Backbone.View.extend({

    initialize:function () {
        this.template = _.template(tpl.get('header_tpl'));
    },

    render:function (eventName) {
        $(this.el).html(this.template(arguments[0]));
        return this;
    },

    events:{
    }
});