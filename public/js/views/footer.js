//handles footer view and related events
window.FooterView = Backbone.View.extend({
	tagName : "div",
	
    render:function (eventName) {
        $(this.el).html(tpl.get('footer_tpl'));
        return this;
    }
});