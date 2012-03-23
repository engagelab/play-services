Backbone.View.prototype.close = function() {
	console.log('Closing view ' + this);
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
		"/commentsbyid/:id" : "listComments"
	},
	
	def : function() {
		app.navigate("/#/groups", true);
	},
	
	listGroups : function() {
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
tpl.loadTemplates(['header_tpl', 'groups_tpl', 'members_tpl', 'comment_tpl'], function() {
	app = new AppRouter();
	Backbone.history.start();
});
