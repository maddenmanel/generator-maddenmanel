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
    }, {
      type: 'string',
      name: 'moduleNameFront',
      message: '请输入Module前缀:',
      default: 'jdpay-example'
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
    }, {
      type: 'checkbox',
      name: 'dependencies',
      message: '请选择依赖:',
      choices: [
        {
          name: 'web',
          value: 'web',
          checked: true
        },
        {
          name: 'tomcat',
          value: 'tomcat',
          checked: true
        },
        {
          name: 'lombok',
          value: 'lombok',
          checked: true
        },
        {
          name: 'test',
          value: 'test',
          checked: true
        },
        {
          name: 'core',
          value: 'core',
          checked: true
        },
        {
          name: 'log',
          value: 'log',
          checked: true
        },
        {
          name: 'jsf',
          value: 'jsf',
          checked: true
        },
        {
          name: 'ump',
          value: 'ump',
          checked: true
        },
        {
          name: 'json',
          value: 'json',
          checked: true
        },
        {
          name: 'jmq',
          value: 'jmq',
          checked: true
        },
        {
          name: 'druid',
          value: 'druid',
          checked: true
        },
        {
          name: 'mybatis',
          value: 'mybatis',
          checked: true
        },
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
  this.template('docs/schema.sql', this.systemName + '/docs/schema.sql');
  this.copy('_gitignore', this.systemName + '/.gitignore');


  // root
  this.template('pom.xml', this.systemName + '/pom.xml');
  this.template('README.md', this.systemName + '/README.md');


  // domainModule
  var domainModule = this.moduleNameFront + '-domain';
  mkdirp(this.systemName + '/' + domainModule);
  this.template('domain/package-info.java', this.systemName + '/' + domainModule + '/src/main/java/' + packageFolder + '/domain' + '/package-info.java');
  this.template('domain/pom.xml', this.systemName + '/' + domainModule + '/pom.xml');
  this.template('domain/User.java', this.systemName + '/' + domainModule + '/src/main/java/' + packageFolder + '/domain' + '/User.java');


  // daoModule
  var repositoryModule = this.moduleNameFront + '-dao';
  mkdirp(this.systemName + '/' + repositoryModule);
  this.template('dao/package-info.java', this.systemName + '/' + repositoryModule + '/src/main/java/' + packageFolder + '/dao' + '/package-info.java');
  this.template('dao/pom.xml', this.systemName + '/' + repositoryModule + '/pom.xml');
  this.template('dao/UserMapper.java', this.systemName + '/' + repositoryModule + '/src/main/java/' + packageFolder + '/dao' + '/UserMapper.java');
  this.fs.copyTpl(
    this.templatePath('dao/resources'),
    this.destinationPath(this.systemName + '/' + repositoryModule + '/src/main/resources/'),
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


  // webModule
  var webModule = this.moduleNameFront + '-web';
  mkdirp(this.systemName + '/' + webModule);
  this.template('web/package-info.java', this.systemName + '/' + webModule + '/src/main/java/' + packageFolder + '/web' + '/package-info.java');
  this.template('web/pom.xml', this.systemName + '/' + webModule + '/pom.xml');
  this.template('web/Application.java', this.systemName + '/' + webModule + '/src/main/java/' + packageFolder + '/web' + '/Application.java');

  this.fs.copyTpl(
    this.templatePath('web/template'),
    this.destinationPath(this.systemName + '/' + webModule + '/src/main/java/' + packageFolder + '/web'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );

  // test
  this.template('web/test/UserServiceTest.java', this.systemName + '/' + webModule + '/src/test/java/' + packageFolder + '/service/biz' + '/UserServiceTest.java');

  this.fs.copyTpl(
    this.templatePath('web/resources'),
    this.destinationPath(this.systemName + '/' + webModule + '/src/main/resources/'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );

  this.config.set('packageName', this.packageName);
  this.config.set('packageFolder', this.packageFolder);
};

