window.Postit = Backbone.Model.extend({});

window.PostitCollection = Backbone.Collection.extend({
    model:Postit,
    url:"http://localhost:9000/postit/"
});