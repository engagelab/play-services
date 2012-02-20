window.Postit = Backbone.Model.extend({
	 urlRoot:"../postits",
    defaults:{
        "id":null,
        "scene":"",
        "owner":""
    }
});

window.PostitCollection = Backbone.Collection.extend({
    model:Postit,
    url:"../postits"
});