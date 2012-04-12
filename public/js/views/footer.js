window.FooterView = Backbone.View.extend({
	tagName : "div",
	
    initialize:function () {
        this.template = _.template(tpl.get('footer_tpl'));
    },

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    }
});