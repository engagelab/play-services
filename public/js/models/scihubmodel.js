/* Group service section */
window.Group = Backbone.Model.extend({});
window.GroupCollection = Backbone.Collection.extend({
	model : Group,
	url : "/groups"
});


/* Postit service section */
window.Postit = Backbone.Model.extend({});
window.PostitCollection = Backbone.Collection.extend({
	model : Postit,
	url : "/commentsbyid"
});
window.PostitComment = Backbone.Model.extend({
	urlRoot:"/fbcomments",
	defaults:{
        "fbcontent":"",
        "comment_id":""
    }
});
window.PostitCommentCollection = Backbone.Collection.extend({
	model : PostitComment,
	url : "/fbcomments"
});


/* Youtube video service section */
window.Video = Backbone.Model.extend({});
window.VideoCollection = Backbone.Collection.extend({
	model : Video,
	url : "/ytvideosbyid"
});
window.VideoComment = Backbone.Model.extend({
	urlRoot:"/fbonvid",
	defaults:{
        "fbcontent":"",
        "vid_id":""
    }
});
window.VideoCommentCollection = Backbone.Collection.extend({
	model : VideoComment,
	url : "/fbonvid"
});


/* Picture service section */
window.Picture = Backbone.Model.extend({});
window.PictureCollection = Backbone.Collection.extend({
	model : Picture,
	url : "/pictures"
});
window.PictureComment = Backbone.Model.extend({
	urlRoot:"/fbonpic",
	defaults:{
        "fbcontent":"",
        "pic_id":""
    }
});
window.PictureCommentCollection = Backbone.Collection.extend({
	model : PictureComment,
	url : "/fbonpic"
});