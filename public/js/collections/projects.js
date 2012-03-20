define([
  'jquery',
  'underscore',
  'backbone',
  'models/projects'
], function($, _, Backbone, projectsModel){
  var projectsCollection = Backbone.Collection.extend({
    model: projectsModel,
    url: "/groups",
    initialize: function(){
    }

  });

  return new projectsCollection;
});
