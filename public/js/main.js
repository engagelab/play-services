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
		"/simulations" : "viewSimulations"
	},
	def : function() {
		app.initPitBoard();
		app.navigate("/#/acts", true);
	},
	//executed when root path called
	listActs : function() {
		this.actlist = new ActCollection();
		this.actlist.fetch({
			data : {
				projectid : "12345"
			},
			success : function(event) {
				app.showView('#stage', new ActPickerView({
					model : app.actlist
				}), 'acts');
			}
		});

		//app.showView('#scenes', new ScenePickerView(), 'menu');
	},
	listScenes : function(acts) {
		console.log("let's get scenes" + acts);
		app.showView('#scenes', new ScenePickerView(), 'menu');
	},
	//create new postit
	newPostit : function() {
		app.postitList.create({
			wait : true,
			user : "fahied",
			sceneId : app.currentViewId
		});
	},
	//switch to simulations view
	viewSimulations : function() {
		app.initPitBoard();
		app.simulation = new SimulationsView();
		app.showView('#scenes', app.simulation, 'simu1');
	},
	//view switcher function
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
	},
	//preloader of collections
	/*before : function(callback) {
	 if(this.postitList) {
	 if(callback)
	 callback();
	 } else {
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
	 if(callback)
	 callback();
	 }
	 });
	 }
	 }*/
});

//loading html templates
tpl.loadTemplates(['header', 'act-picker', 'postit-item', 'scene-picker', 'simulations'], function() {
	app = new AppRouter();
	Backbone.history.start();
});
