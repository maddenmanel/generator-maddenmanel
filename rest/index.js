'use strict';
var util = require('util');
var yeoman = require('yeoman-generator');
var mkdirp = require('mkdirp');

var RestGenerator = module.exports = function RestGenerator(args, options, config) {
  yeoman.generators.Base.apply(this, arguments);
};

util.inherits(RestGenerator, yeoman.generators.Base);

RestGenerator.prototype.askFor = function askFor() {
  var cb = this.async();

  var prompts = [
    {
      type: 'string',
      name: 'packageName',
      message: '(1/4) 请输入包名称:',
      default: this.config.get('packageName')
    },
    {
      type: 'string',
      name: 'representation',
      message: '(2/4) Name for your representation class:',
      default: 'MyThing'
    },
    {
      type: 'string',
      name: 'controllerName',
      message: '(3/4) Name for your controller:',
      default: 'MyController'
    },
    {
      type: 'string',
      name: 'controllerPath',
      message: '(4/4) Path to Controller:',
      default: '/hello-world'
    }
  ]

  this.prompt(prompts, function (props) {
    this.packageName = props.packageName;
    this.representation = props.representation;
    this.controllerName = props.controllerName;
    this.controllerPath = props.controllerPath;
    cb();
  }.bind(this));

};

RestGenerator.prototype.files = function app() {
  var packageFolder = this.packageName.replace(/\./g, '/');
  var controllersDir = 'src/main/java/' + packageFolder + '/controller';
  var domainsDir = 'src/main/java/' + packageFolder + '/domain';
  var commonDir = 'src/main/java/' + packageFolder + '/common';
  var configrationDir = 'src/main/java/' + packageFolder + '/configration';
  var interceptorDir = 'src/main/java/' + packageFolder + '/interceptor';

  mkdirp(controllersDir);
  mkdirp(domainsDir);
  mkdirp(commonDir);
  mkdirp(configrationDir);
  mkdirp(interceptorDir);

  this.template('Controller.java', controllersDir + '/' + this.controllerName + '.java');
  this.template('Representation.java', domainsDir + '/' + this.representation + '.java');

  this.config.set('packageName', this.packageName);
};
