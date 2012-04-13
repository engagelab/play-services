//init backone function
Backbone.View.prototype.close = function() {
	if(this.beforeClose) {
		this.beforeClose();
	}
	this.remove();
	this.unbind();
};

//backbone routes handler
var AppRouter = Backbone.Router.extend({

	initialize : function() {
		//static header and footer
		$('#header').html(new HeaderView('null').render().el);
		$('#footer').html(new FooterView().render().el);
	},
	
	routes : {
		"" : "def",
		"/groups" : "listGroups",
		"/resources" : "listResources",
		"/groupcontent/:id/:nam/:tab" : "listComments",
		"/simulations" : "showSimulations",
		"/grpresources" : "listGrpResources"
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
	
	listGrpResources : function() {
		$('#header').html(new HeaderView('null').render().el);
		app.showView('#resources', new GrpResourceView());
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
		//update the header with chosen group
		$('#header').html(new HeaderView(nam).render().el);
		
		//show the tabs
		app.showView('#stage', new ActivityView({ grpid:id, grpname:nam }));
		this.activityMode = tab;
		
		//fetch the pictures for selected group
		this.picList = new PicCollection();
		this.picList.fetch({ data: $.param({ grpid: id }),
			success : function(event) {
				$('#acCont').append(new PicView({ model:app.picList, mmode:app.activityMode }).render().el);
			},
			wait: true 
		});
		
		//fetch the videos for selected group
		this.ytvideoList = new YTVideoCollection();
		this.ytvideoList.fetch({ data: $.param({ grpid: id }),
			success : function(event) {
				$('#acCont').append(new YTVideoView({ model:app.ytvideoList, mmode:app.activityMode }).render().el);
			},
			wait: true 
		});
		
		//fetch the post-its for selected group
		this.commentList = new CommentCollection();
		this.commentList.fetch({ data: $.param({ grpid: id }),
			success : function(event) {
				$('#acCont').append(new CommentView({ model:app.commentList, mmode:app.activityMode }).render().el);
			},
			wait: true
		});
		
		//select the clicked tab
		if(tab==1) {
			$('#fuzzy').addClass("tabselected");
		}
		else if(tab==2) {
			$('#timeline').addClass("tabselected");
		} 
	},
	
	//switches between backbone views, closing the currently opened one
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
	'activity_tpl',
	'pic_tpl',
	'grpresource_tpl',
	'footer_tpl'
	], function() {
	app = new AppRouter();
	Backbone.history.start();
});


/******* fixes the blue frame flicking in chrome when dragging objects (http://forum.jquery.com/topic/chrome-text-select-cursor-on-drag) *****/
document.onselectstart = function () { return false; };

$(document).ready(function() {
    $("#footer").pinFooter();
});

$(window).resize(function() {
    $("#footer").pinFooter();
});