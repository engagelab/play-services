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
		"/acts" : "listActs",
		"/scenes/:id" : "listScenes",
		"/simulation/:id" : "viewSimulation"
	},
	
	def : function() {
		app.initPitBoard();
		app.navigate("/#/acts", true);
	},
	
	listActs : function() {
		this.actlist = new ActCollection();
		this.actlist.fetch({
			data : {
				projectid : "12345"
			},
			success : function(event) {
				app.showView('#stage', new ActPickerView({model : app.actlist}), 'root');
			}
		});
	},
	
	listScenes : function(id) {
		this.scenelist = new SceneCollection();
		this.scenelist.fetch({
			data : {
				actid : id
			},
			success : function(event) {
				app.showView('#stage', new ScenePickerView({model : app.scenelist}), 'acts'+id);
			}
		});
	},
	
	viewSimulation : function(id) {
		app.initPitBoard();
		app.simulation = new SimulationsView();
		app.showView('#stage', app.simulation, 'simu'+id);
	},
	
	newPostit : function() {
		app.postitList.create({
			wait : true,
			user : "fahied",
			sceneId : app.currentViewId
		});
	},
	
	
	showView : function(selector, view, viewid) {
		if(this.currentView) {
			this.currentView.close();
		}
		$(selector).html(view.render().el);
		this.currentView = view;
		this.currentViewId = viewid;
		//reload the postits for the scene
		this.retrievePostIts();
		return view;
	},
	
	initPitBoard : function() {
		$('#board').html('');
		app.postitList = null;
	},
	
	retrievePostIts : function() {
		this.postitList = new PostitCollection();
		this.postitList.fetch({
			data : {
				user : "fahied",
				sceneId : app.currentViewId
			},
			success : function() {
				$('#board').html(new PostitListView({
					model : app.postitList
				}).render().el);
			}
		});
	}
});

//loading html templates
tpl.loadTemplates(['header', 'act-picker', 'postit-item', 'scene-picker', 'simulations'], function() {
	app = new AppRouter();
	Backbone.history.start();
});
