window.GroupListView = Backbone.View.extend({
	tagName : "div",
	className : "groupContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		$(this.el).html('');
		_.each(this.model.models, function(group) {
			$(this.el).append(new GroupListItemView({model : group}).render().el);
		}, this);
		return this;
	}
});

window.GroupListItemView = Backbone.View.extend({
	
	className : 'group-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('groups_tpl'));
	},
	
	events : {
		"click" : "getCommentsByGroup"
	},

	getCommentsByGroup : function(event) {
		app.selectedGroupName = this.model.attributes.name;
		app.navigate("/#/commentsbyid/"+this.model.attributes.id, true);
		//app.navigate("/#/contents/"+this.model.attributes.id, true);
	},
	
	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		_.each(this.model.attributes.members, function(member) {
			$(this.el).append(new MemberListItemView({model : member}).render().el);
		}, this);
		return this;
	}
});

window.MemberListItemView = Backbone.View.extend({
	className : 'member-ui',
	
	initialize : function() {
		this.template = _.template(tpl.get('members_tpl'));
	},
	
	events : {
		
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model.user));
		return this;
	}
});
