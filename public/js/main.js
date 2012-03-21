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
		"/groups" : "listGroups"
	},
	
	def : function() {
		app.navigate("/#/groups", true);
	},
	
	listGroups : function() {
		this.groupList = new GroupCollection();
		this.groupList.fetch({success:function () {
				$('#groups').html(new GroupListView({model : app.groupList}).render().el);
			}
		});
	},
	
	showView : function(selector, view, viewid) {
		if(this.currentView) {
			this.currentView.close();
		}
		$(selector).html(view.render().el);
		this.currentView = view;
		this.currentViewId = viewid;
		return view;
	}
});

//loading html templates
tpl.loadTemplates(['header_tpl', 'groups_tpl', 'members_tpl'], function() {
	app = new AppRouter();
	Backbone.history.start();
});
