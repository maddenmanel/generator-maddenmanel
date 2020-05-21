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
      default: '2.2.6.RELEASE'
    }, {
      type: 'string',
      name: 'packageName',
      message: '请输入包名:',
      default: 'com.jdd'
    }, {
      type: 'string',
      name: 'systemName',
      message: '请输入系统名称:',
      default: 'jdpay-example-center'
    },{
      type: 'string',
      name: 'moduleNameFront',
      message: '请输入Module前缀:',
      default: 'jdpay-example'
    },  {
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
    }, {
      type: 'checkbox',
      name: 'dependencies',
      message: '请选择依赖:',
      choices: [
        {
          name: 'jsf',
          value: 'jsf',
          checked: true
        }, {
          name: 'jmq',
          value: 'jmq',
          checked: true
        }, {
          name: 'ump',
          value: 'ump',
          checked: true
        }, {
          name: 'json',
          value: 'json',
          checked: true
        }, {
          name: 'log',
          value: 'log',
          checked: true
        }
      ]
    },
  ];

  this.prompt(prompts, function (props) {
    this.bootVersion = props.bootVersion;
    this.packageName = props.packageName;
    this.systemName = props.systemName;
    this.moduleNameFront = props.moduleNameFront;
    this.javaVersion = props.javaVersion;
    this.packagingType = props.packagingType;
    this.baseName = this.moduleNameFront.replace(/\-/g, '.').replace(/\_/g, '.');

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


  var packageFolder = this.packageName.replace(/\./g, '/') + '/' + this.moduleNameFront.replace(/\-/g, '/').replace(/\_/g, '/');
  mkdirp(this.systemName);

  // docs
  this.template('docs/business.md', this.systemName + '/docs/business.md');
  this.copy('_gitignore', this.systemName + '/.gitignore');


  // root
  this.template('pom.xml', this.systemName + '/pom.xml');
  this.template('README.md', this.systemName + '/README.md');


  // domainModule
  var domainModule = this.moduleNameFront + '-domain';
  mkdirp(this.systemName + '/' + domainModule);
  this.template('domain/package-info.java', this.systemName + '/' + domainModule + '/src/main/java/' + packageFolder + '/domain' + '/package-info.java');
  this.template('domain/pom.xml', this.systemName + '/' + domainModule + '/pom.xml');


  // infrastructureModule
  var infrastructureModule = this.moduleNameFront + '-infrastructure';
  mkdirp(this.systemName + '/' + infrastructureModule);
  this.template('infrastructure/package-info.java', this.systemName + '/' + infrastructureModule + '/src/main/java/' + packageFolder + '/infrastructure' + '/package-info.java');
  this.template('infrastructure/pom.xml', this.systemName + '/' + infrastructureModule + '/pom.xml');

  this.fs.copyTpl(
    this.templatePath('infrastructure/template'),
    this.destinationPath(this.systemName + '/' + infrastructureModule + '/src/main/java/' + packageFolder + '/infrastructure'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );


  // interfacesModule
  var interfacesModule = this.moduleNameFront + '-interfaces';
  mkdirp(this.systemName + '/' + interfacesModule);
  this.template('interfaces/package-info.java', this.systemName + '/' + interfacesModule + '/src/main/java/' + packageFolder + '/interfaces' + '/package-info.java');
  this.template('interfaces/pom.xml', this.systemName + '/' + interfacesModule + '/pom.xml');

  this.fs.copyTpl(
    this.templatePath('interfaces/template'),
    this.destinationPath(this.systemName + '/' + interfacesModule + '/src/main/java/' + packageFolder + '/interfaces'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );


  // serviceModule
  var serviceModule = this.moduleNameFront + '-service';
  mkdirp(this.systemName + '/' + serviceModule);
  this.template('service/package-info.java', this.systemName + '/' + serviceModule + '/src/main/java/' + packageFolder + '/service' + '/package-info.java');
  this.template('service/pom.xml', this.systemName + '/' + serviceModule + '/pom.xml');

  this.fs.copyTpl(
    this.templatePath('service/template'),
    this.destinationPath(this.systemName + '/' + serviceModule + '/src/main/java/' + packageFolder + '/service'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );

  // startModule
  var startModule = this.moduleNameFront + '-start';
  mkdirp(this.systemName + '/' + startModule);
  this.template('start/package-info.java', this.systemName + '/' + startModule + '/src/main/java/' + packageFolder + '/start' + '/package-info.java');
  this.template('start/Application.java',  this.systemName + '/' + startModule + '/src/main/java/' + packageFolder + '/start' + '/Application.java');
  this.template('start/pom.xml', this.systemName + '/' + startModule + '/pom.xml');
  this.fs.copyTpl(
    this.templatePath('start/template/resources'),
    this.destinationPath(this.systemName + '/' + startModule + '/src/main/resources/'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );

  this.config.set('packageName', this.packageName);
  this.config.set('packageFolder', this.packageFolder);
};

