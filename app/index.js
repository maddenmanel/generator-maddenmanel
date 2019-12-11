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
      name: 'systemName',
      message: '请输入系统名称:',
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
    this.systemName = props.systemName;
    this.javaVersion = props.javaVersion;
    this.packagingType = props.packagingType;
    this.baseName = this.systemName.replace(/\-/g,'.').replace(/\_/g,'.');

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
  var packageFolder = this.packageName.replace(/\./g, '/')+'/' + this.systemName.replace(/\-/g,'/').replace(/\_/g,'/');
  var srcDir = this.systemName + '/src/main/java/' + packageFolder;
  var testDir = this.systemName + '/src/test/java/' +packageFolder;

  var controllerDir = this.systemName + '/src/main/java/' + packageFolder + '/controller';
  var serviceDir = this.systemName + '/src/main/java/' + packageFolder + '/service';
  var serviceImplDir = this.systemName + '/src/main/java/' + packageFolder + '/service/impl';
  var daoDir = this.systemName + '/src/main/java/' + packageFolder + '/dao';
  var domainDir = this.systemName + '/src/main/java/' + packageFolder + '/domain';
  var commonDir = this.systemName + '/src/main/java/' + packageFolder + '/common';
  var configrationDir = this.systemName + '/src/main/java/' + packageFolder + '/configration';
  var interceptorDir = this.systemName + '/src/main/java/' + packageFolder + '/interceptor';
  var constantsDir = this.systemName + '/src/main/java/' + packageFolder + '/constants';
  var exceptionDir = this.systemName + '/src/main/java/' + packageFolder + '/exception';
  var producerDir = this.systemName + '/src/main/java/' + packageFolder + '/jmq/producer';
  var consumerDir = this.systemName + '/src/main/java/' + packageFolder + '/jmq/consumer';
  var annotationDir = this.systemName + '/src/main/java/' + packageFolder + '/common/annotation';
  var gatewayDir = this.systemName + '/src/main/java/' + packageFolder + '/gataway';
  var rpcDir = this.systemName + '/src/main/java/' + packageFolder + '/rpc';


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
  mkdirp(constantsDir);
  mkdirp(exceptionDir);
  mkdirp(producerDir);
  mkdirp(consumerDir);
  mkdirp(annotationDir);
  mkdirp(gatewayDir);
  mkdirp(rpcDir);

  // Template.
  this.template('pom.xml', this.systemName + '/pom.xml');
  // this.template('.gitignore', this.systemName + '/.gitignore');

  this.template('Application.java', srcDir + '/Application.java');

  // common
  this.template('GsonUtil.java', commonDir + '/GsonUtil.java');
  this.template('ValidateErrorsVO.java', commonDir + '/ValidateErrorsVO.java');
  this.template('GateWayExceptionConvert.java', commonDir + '/GateWayExceptionConvert.java');
  this.template('JsfClientContextFilter.java', commonDir + '/JsfClientContextFilter.java');
  this.template('AnnotatedElementCacheUtils.java', commonDir + '/AnnotatedElementCacheUtils.java');
  this.template('JacksonUtil.java', commonDir + '/JacksonUtil.java');
  this.template('BindingResultUtils.java', commonDir + '/BindingResultUtils.java');
  this.template('MessageFormatter.java', commonDir + '/MessageFormatter.java');
  this.template('ITraceIdFactory.java', commonDir + '/ITraceIdFactory.java');
  this.template('FieldErrorVO.java', commonDir + '/FieldErrorVO.java');
  this.template('TraceIdFactory.java', commonDir + '/TraceIdFactory.java');
  this.template('IGateWayFinally.java', commonDir + '/IGateWayFinally.java');
  this.template('ClientContext.java', commonDir + '/ClientContext.java');
  this.template('SystemIpUtil.java', commonDir + '/SystemIpUtil.java');
  this.template('MDCFinally.java', commonDir + '/MDCFinally.java');
  this.template('ClientInfo.java', commonDir + '/ClientInfo.java');
  this.template('UUIDUtil.java', commonDir + '/UUIDUtil.java');

  // ump
  this.template('UMPCaller.java', commonDir + '/ump/UMPCaller.java');
  this.template('VoidCaller.java', commonDir + '/ump/VoidCaller.java');

  // constants
  this.template('CacheConfig.java', configrationDir + '/CacheConfig.java');
  this.template('UmpConstants.java', constantsDir + '/UmpConstants.java');
  this.template('TraceConstants.java', constantsDir + '/TraceConstants.java');
  this.template('ErrorCodeConstants.java', constantsDir + '/ErrorCodeConstants.java');

  // exception
  this.template('JmqSendException.java', exceptionDir + '/JmqSendException.java');
  this.template('BizException.java', exceptionDir + '/BizException.java');
  this.template('BindException.java', exceptionDir + '/BindException.java');
  this.template('BizExceptionConvert.java', exceptionDir + '/BizExceptionConvert.java');

  // gateway
  this.template('UserGateway.java', gatewayDir + '/UserGateway.java');

  // rpc
  this.template('OrderIdRPC.java', rpcDir + '/OrderIdRPC.java');

  // jmq
  this.template('JmqProducer.java', producerDir + '/JmqProducer.java');
  this.template('UserAddListener.java', consumerDir + '/UserAddListener.java');
  this.template('BaseJmqMessageListener.java', consumerDir + '/BaseJmqMessageListener.java');

  // example
  this.template('User.java', domainDir + '/User.java');
  this.template('UserService.java', serviceDir + '/UserService.java');
  this.template('UserServiceImpl.java', serviceImplDir + '/UserServiceImpl.java');
  this.template('UserMapper.java', daoDir + '/UserMapper.java');
  this.template('UserMapper.java', daoDir + '/UserMapper.java');

  // interceptor
  this.template('LoginInterceptor.java', interceptorDir + '/LoginInterceptor.java');
  this.template('GateWayInterceptor.java', interceptorDir + '/GateWayInterceptor.java');
  this.template('LoggableInterceptor.java', interceptorDir + '/LoggableInterceptor.java');

  // controller
  this.template('IndexViewController.java', controllerDir + '/IndexViewController.java');
  this.template('LoginViewController.java', controllerDir + '/LoginViewController.java');

  // common annotation
  this.template('GateWay.java', annotationDir + '/GateWay.java');
  this.template('Loggable.java', annotationDir + '/Loggable.java');

  // test
  this.template('UserServiceTest.java', testDir + '/service/UserServiceTest.java');
  this.template('BaseTest.java', testDir + '/BaseTest.java');
  this.template('JmqProducerTest.java', testDir + '/jmq/producer/JmqProducerTest.java');
  this.template('UserGatewayTest.java', testDir + '/gateway/UserGatewayTest.java');
  this.template('OrderIdRPC.java', testDir + '/rpc/OrderIdRPC.java');

  this.config.set('packageName', this.packageName);
  this.config.set('packageFolder', this.packageFolder);
};


// Resource template copy.
SpringGenerator.prototype.writing = function writing() {
  this.fs.copyTpl(
    this.templatePath('resources'),
    this.destinationPath(this.systemName + '/src/main/resources/'),
    {systemName: this.systemName, packageName: this.packageName, baseName: this.baseName}
  );
};

SpringGenerator.prototype.projectfiles = function projectfiles() {
};
