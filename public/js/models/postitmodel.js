window.Postit = Backbone.Model.extend({});

window.PostitCollection = Backbone.Collection.extend({
	model : Postit,
	url : "/commentsbyGroup/"
});

window.Act = Backbone.Model.extend({});
window.ActCollection = Backbone.Collection.extend({
	model : Act,
	url : "/acts/"
});

window.Scene = Backbone.Model.extend({});
window.SceneCollection = Backbone.Collection.extend({
	model : Scene,
	url : "/scenes/"
});

window.Task = Backbone.Model.extend({});
window.TasksCollection = Backbone.Collection.extend({
	model : Task,
	url : "/task/"
});

window.Group = Backbone.Model.extend({});
window.GroupsCollection = Backbone.Collection.extend({
	model : Group,
	url : "/groups"
});