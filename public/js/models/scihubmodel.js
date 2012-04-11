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

window.YTVideo = Backbone.Model.extend({});

window.YTVideoCollection = Backbone.Collection.extend({
	model : YTVideo,
	url : "/ytvideosbyid"
});

window.FBComment = Backbone.Model.extend({
	urlRoot:"/fbcomments",
	defaults:{
        "fbcontent":"",
        "comment_id":""
    }
});

window.FBCommentCollection = Backbone.Collection.extend({
	model : FBComment,
	url : "/fbcomments"
});

window.Pic = Backbone.Model.extend({});

window.PicCollection = Backbone.Collection.extend({
	model : YTVideo,
	url : "/pictures"
});
