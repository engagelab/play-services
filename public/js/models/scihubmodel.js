window.Group = Backbone.Model.extend({});

window.GroupCollection = Backbone.Collection.extend({
	model : Group,
	url : "/groups"
});