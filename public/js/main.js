Backbone.View.prototype.close = function() {
	if(this.beforeClose) {
		this.beforeClose();
	}
	this.remove();
	this.unbind();
};
var AppRouter = Backbone.Router.extend({

	initialize : function() {
		$('#header').html(new HeaderView('null').render().el);
	},
	
	routes : {
		"" : "def",
		"/groups" : "listGroups",
		"/resources" : "listResources",
		"/groupcontent/:id/:nam" : "listComments",
		"/simulations" : "showSimulations"
	},
	
	def : function() {
		app.navigate("/#/groups", true);
	},
	
	showSimulations : function() {
		$('#header').html(new HeaderView('null').render().el);
		app.showView('#stage', new SimulationView());
	},
	
	listResources : function() {
		$('#header').html(new HeaderView('null').render().el);
		app.showView('#stage', new ResourceView());
	},
	
	listGroups : function() {
		$('#header').html(new HeaderView('null').render().el);
		this.selectedGroupName = '';
		this.groupList = new GroupCollection();
		this.groupList.fetch({success:function () {
				app.showView('#stage', new GroupListView({model : app.groupList}));
			},
			wait: true
		});
	},
	
	listComments : function(id, nam) {
		$('#header').html(new HeaderView(nam).render().el);
		$('#stage').html('');
		
		this.commentList = new CommentCollection();
		this.commentList.fetch({ data: $.param({ grpid: id}),
			success : function(event) {
				$('#stage').append(new CommentView({model : app.commentList}).render().el);
			},
			wait: true
		});
		
		this.ytvideoList = new YTVideoCollection();
		this.ytvideoList.fetch({ data: $.param({ grpid: id}),
			success : function(event) {
				$('#stage').append(new YTVideoView({model : app.ytvideoList}).render().el);
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
tpl.loadTemplates([
	'header_tpl',
	'groups_tpl',
	'members_tpl',
	'comment_tpl',
	'resource_tpl',
	'ytvideo_tpl',
	'fbcomment_tpl',
	'newfbcomment_tpl',
	'simulations_tpl'
	], function() {
	app = new AppRouter();
	Backbone.history.start();
});
