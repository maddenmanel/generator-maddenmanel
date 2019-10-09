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
      name: 'bootVersion',
      message: '请输入Spring Boot的版本:',
      default: '2.1.8.RELEASE'
    }, {
      type: 'string',
      name: 'packageName',
      message: '请输入包名:',
      default: 'com.jdd'
    }, {
      type: 'string',
      name: 'baseName',
      message: '请输入应用名称:',
      default: 'app'
    }, {
      type: 'string',
      name: 'javaVersion',
      message: '请输入Java的版本:',
      default: '1.8'
    }, {
      type: 'checkbox',
      name: 'packagingType',
      message: '请输入打包类型:',
      choices: [
        {
          name: 'Jar',
          value: 'jar'
        }, {
          name: 'War',
          value: 'war'
        }
      ]
    },
  ];

  this.prompt(prompts, function (props) {
    this.bootVersion = props.bootVersion;
    this.packageName = props.packageName;
    this.baseName = props.baseName;
    this.javaVersion = props.javaVersion;
    this.packagingType = props.packagingType;

    // Packaging Type
    var hasPackagingType = function (packagingTypeStarter) {
      return props.packagingType.indexOf(packagingTypeStarter) !== -1;
    };
    this.jar = hasPackagingType('jar');
    this.war = hasPackagingType('war');

    cb();
  }.bind(this));
};

SpringGenerator.prototype.app = function app() {
  var packageFolder = this.packageName.replace(/\./g, '/') + '/' + this.baseName.replace('-','/').replace('_','/');

  var srcDir = this.baseName + '/src/main/java/' + packageFolder;
  var testDir = this.baseName + '/src/test/java/' +packageFolder;

  var controllerDir = this.baseName + '/src/main/java/' + packageFolder + '/controller';
  var serviceDir = this.baseName + '/src/main/java/' + packageFolder + '/service';
  var serviceImplDir = this.baseName + '/src/main/java/' + packageFolder + '/service/impl';
  var daoDir = this.baseName + '/src/main/java/' + packageFolder + '/dao';
  var domainDir = this.baseName + '/src/main/java/' + packageFolder + '/domain';
  var commonDir = this.baseName + '/src/main/java/' + packageFolder + '/common';
  var configrationDir = this.baseName + '/src/main/java/' + packageFolder + '/configration';
  var interceptorDir = this.baseName + '/src/main/java/' + packageFolder + '/interceptor';

  // Mkdir.
  mkdirp(srcDir);
  mkdirp(testDir);
  mkdirp(controllerDir);
  mkdirp(serviceDir);
  mkdirp(serviceImplDir);
  mkdirp(daoDir);
  mkdirp(domainDir);
  mkdirp(commonDir);
  mkdirp(configrationDir);
  mkdirp(interceptorDir);

  // Template.
  this.template('pom.xml', this.baseName + '/pom.xml');
  this.template('Application.java', srcDir + '/Application.java');
  this.template('BaseTest.java', testDir + '/BaseTest.java');
  this.template('GsonUtil.java', commonDir + '/GsonUtil.java');

  // example
  this.template('User.java', domainDir + '/User.java');
  this.template('UserService.java', serviceDir + '/UserService.java');
  this.template('UserServiceImpl.java', serviceImplDir + '/UserServiceImpl.java');
  this.template('UserMapper.java', daoDir + '/UserMapper.java');
  this.template('UserMapper.java', daoDir + '/UserMapper.java');

  this.template('UserServiceTest.java', testDir + '/service/UserServiceTest.java');


  // common
  this.template('LoginInterceptor.java', interceptorDir + '/LoginInterceptor.java');
  this.template('IndexViewController.java', controllerDir + '/IndexViewController.java');
  this.template('LoginViewController.java', controllerDir + '/LoginViewController.java');

  this.config.set('packageName', this.packageName);
  this.config.set('packageFolder', packageFolder);
};


// Resource template copy.
SpringGenerator.prototype.writing = function writing() {
  this.fs.copyTpl(
    this.templatePath('resources'),
    this.destinationPath(this.baseName + '/src/main/resources/'),
    {baseName: this.baseName, packageName: this.packageName}
  );
};

SpringGenerator.prototype.projectfiles = function projectfiles() {
};
