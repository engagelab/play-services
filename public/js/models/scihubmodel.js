window.Group = Backbone.Model.extend({});

window.GroupCollection = Backbone.Collection.extend({
	model : Group,
	url : "/groups"
});

window.Comment = Backbone.Model.extend({});

window.CommentCollection = Backbone.Collection.extend({
	model : Comment,
	url : "/commentsbyid"
});

window.Content = Backbone.Model.extend({});

window.ContentCollection = Backbone.Collection.extend({
	model : Content,
	url : "/contents"
});