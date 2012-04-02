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
		"/groupcontent/:id/:nam/:tab" : "listComments",
		"/simulations" : "showSimulations"
	},
	
	def : function() {
		app.navigate("/#/groups", true);
	},
	
	showSimulations : function() {
		$('#header').html(new HeaderView('null').render().el);
		app.showView('#resources', new SimulationView());
	},
	
	listResources : function() {
		$('#header').html(new HeaderView('null').render().el);
		app.showView('#resources', new ResourceView());
	},
	
	listGroups : function() {
		$('#header').html(new HeaderView('null').render().el);
		this.selectedGroupName = '';
		this.groupList = new GroupCollection();
		this.groupList.fetch({success:function () {
				app.showView('#groups', new GroupListView({model : app.groupList}));
			},
			wait: true
		});
	},
	
	listComments : function(id, nam, tab) {
		$('#header').html(new HeaderView(nam).render().el);
		app.showView('#stage', new ActivityView({grpid:id,grpname:nam}));
		this.activityMode = tab;
		
		this.ytvideoList = new YTVideoCollection();
		this.ytvideoList.fetch({ data: $.param({ grpid: id}),
			success : function(event) {
				$('#acCont').append(new YTVideoView({model : app.ytvideoList, mmode: app.activityMode}).render().el);
			},
			wait: true 
		});
		
		this.commentList = new CommentCollection();
		this.commentList.fetch({ data: $.param({ grpid: id}),
			success : function(event) {
				$('#acCont').append(new CommentView({model : app.commentList, mmode: app.activityMode}).render().el);
			},
			wait: true
		});
		
		if(tab==1) {
			$('#fuzzy').addClass("tabselected");
		}
		else if(tab==2) {
			$('#timeline').addClass("tabselected");
		} 
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
	'simulations_tpl',
	'activity_tpl'
	], function() {
	app = new AppRouter();
	Backbone.history.start();
});
