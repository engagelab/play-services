window.Postit = Backbone.Model.extend({});
window.PostitCollection = Backbone.Collection.extend({
	model : Postit,
	url : "/postit/"
});
