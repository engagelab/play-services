window.YTVideoView = Backbone.View.extend({
	tagName : "div",
	className : "ytvideoContainer",
	
    initialize : function() {
		this.model.bind("reset", this.render, this);
		this.model.bind("add", this.render, this);
	},
	
	render : function(eventName) {
		_.each(this.model.models, function(content) {
			$(this.el).append(new YTVideoItemView({model : content, mmode:this.options.mmode}).render().el);
		}, this);
		return this;
	}
});

window.YTVideoItemView = Backbone.View.extend({
	
	initialize : function() {
		this.template = _.template(tpl.get('ytvideo_tpl'));
	},
	
	updatePosition : function(event) {
		var pleft = this.el.style.left;
		var ptop = this.el.style.top;
		this.model.attributes.wxpos = pleft.substring(0, pleft.length-2);;
		this.model.attributes.wypos = ptop.substring(0, ptop.length-2);
		this.model.save();
	},
	
	
	events : {
		"dragstop" : "updatePosition"
	},

	render : function(eventName) {
		if(this.options.mmode == 1) {
			$(this.el).attr('style', 'left:' + this.model.attributes.wxpos + 'px;top:' + this.model.attributes.wypos + 'px');
		}
		$(this.el).html(this.template(this.model.toJSON()));
		
		if(this.options.mmode == 1) {
			$(this.el).addClass('ytvideo-ui');
			$(this.el).draggable({
				//handle : '.toolbar',
				stack: "div",
				containment: 'acCont' 
			});
		}
		else {
			$(this.el).addClass('ytvideo-ui-tl');
		}
		return this;
	}
});