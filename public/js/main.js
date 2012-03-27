Backbone.View.prototype.close = function() {
	if(this.beforeClose) {
		this.beforeClose();
	}
	this.remove();
	this.unbind();
};
var AppRouter = Backbone.Router.extend({

	initialize : function() {
		$('#header').html(new HeaderView().render().el);
	},
	
	routes : {
		"" : "def",
		"/groups" : "listGroups",
		"/resources" : "listResources",
		"/commentsbyid/:id" : "listComments",
		"/contents/:id" : "listContents"
	},
	
	def : function() {
		app.navigate("/#/groups", true);
	},
	
	listResources : function() {
		app.showView('#stage', new ResourceView());
	},
	
	listGroups : function() {
		$('#stage').html('');
		this.selectedGroupName = '';
		this.groupList = new GroupCollection();
		this.groupList.fetch({success:function () {
				app.showView('#stage', new GroupListView({model : app.groupList}));
			},
			wait: true
		});
	},
	
	listComments : function(id) {
		this.commentList = new CommentCollection();
		this.commentList.fetch({ data: $.param({ grpid: id}),
			success : function(event) {
				app.showView('#stage', new CommentView({model : app.commentList}));
			},
			wait: true
		});
	},
	
	listContents : function(id) {
		this.contentList = new ContentCollection();
		this.contentList.fetch({ data: $.param({ grpid: id}),
			success : function(event) {
				app.showView('#stage', new ContentView({model : app.contentList}));
			},
			wait: true
		});
	},
	
	showView : function(selector, view) {
		if(this.currentView) {
			this.currentView.close();
		}
		$(selector).html(view.render().el);
		this.currentView = view;
		return view;
	}
});

//loading html templates
tpl.loadTemplates(['header_tpl', 'groups_tpl', 'members_tpl', 'comment_tpl', 'content_tpl', 'resource_tpl', 'ytvideo_tpl'], function() {
	app = new AppRouter();
	Backbone.history.start();
});
