'use strict';
var util = require('util');
var yeoman = require('yeoman-generator');
var chalk = require('chalk');
var mkdirp = require('mkdirp');

var SpringGenerator = module.exports = function SpringGenerator(args, options, config) {
  yeoman.generators.Base.apply(this, arguments);
};

util.inherits(SpringGenerator, yeoman.generators.Base);

SpringGenerator.prototype.askFor = function askFor() {
  var cb = this.async();

  console.log(chalk.green('\n.............DD88888888888888888,............\n' +
    '...........:888888888888888888888,...........\n' +
    '..........+88888888888888888888888+..........\n' +
    '.........,8888888888888888888888888..........\n' +
    '.........888888888888...888888888888.........\n' +
    '.......,88888887..D88...88Z..88888888,.......\n' +
    '.......8888888,...888...88D...=8888888.......\n' +
    '......D888888,..$8888...88887...8888888......\n' +
    '.....Z888888$..I88888...88888:..88888888,....\n' +
    '....D8888888...888888...88888D..,88888888....\n' +
    '....88888888,..888888..,888888...88888888....\n' +
    '....88888888,..8888888$888888D..,88888888....\n' +
    '....88888888I..Z8888888888888+..888888888....\n' +
    '.....Z8888888...O888888888888..,88888888.....\n' +
    '......88888888...,88888888D...,88888888......\n' +
    '.......88888888=.....?I+.....I88888888.......\n' +
    '.......,88888888D7.........ZD88888888,.......\n' +
    '.........888888888888888888888888888.........\n' +
    '.........,8888888888888888888888888..........\n' +
    '..........+88888888888888888888888+..........\n' +
    '...........,888888888888888888888:...........\n' +
    '.............DD888888888888888DD.............\n' +
    chalk.yellow('\nWelcome to the Spring Boot Generator\n\nLets get started!\n\n')));


  var prompts = [
    {
      type: 'string',
      name: 'packageName',
      message: '请输入包名:',
      default: 'com.jdd'
    }, {
      type: 'string',
      name: 'systemName',
      message: '请输入应用名称:',
      default: 'app'
    }
  ];

  this.prompt(prompts, function (props) {
    this.packageName = props.packageName;
    this.systemName = props.systemName;
    this.baseName = this.systemName.replace(/\-/g,'.').replace(/\_/g,'.');
    cb();
  }.bind(this));
};

SpringGenerator.prototype.app = function app() {
  var packageFolder = this.packageName.replace(/\./g, '/') + '/' + this.baseName.replace(/\./g,'/');

  var srcDir = this.systemName + '/src/main/java/' + packageFolder;
  var moDir = this.systemName + '/src/main/java/' + packageFolder + '/mo';
  var facadeDir = this.systemName + '/src/main/java/' + packageFolder + '/facade';

  // Mkdir.
  mkdirp(srcDir);
  mkdirp(moDir);
  mkdirp(facadeDir);

  // Template.
  this.template('pom.xml', this.systemName + '/pom.xml');
  this.template('Request.java', srcDir + '/Request.java');
  this.template('Response.java', srcDir + '/Response.java');
  this.template('UserMO.java', moDir + '/UserMO.java');
  this.template('UserServiceProvider.java', facadeDir + '/UserServiceProvider.java');

  this.config.set('packageName', this.packageName);
  this.config.set('packageFolder', packageFolder);
};


SpringGenerator.prototype.projectfiles = function projectfiles() {
};
