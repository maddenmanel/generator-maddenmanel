# generator-unionstars [![Build Status](https://travis-ci.org/unionstars/generator-unionstars.svg?branch=master)](https://travis-ci.org/unionstars/generator-unionstars)

A [Yeoman](http://yeoman.io) generator for scaffolding and bootstrapping [Spring Boot](http://projects.spring.io/spring-boot/) and [Spring Cloud](http://projects.spring.io/spring-cloud/) applications. Provides the same selectable options as [Spring Initializr](http://start.spring.io), but with and interactive CLI interface so your hands can stay where they belong, on the keyboard!

## 适用对象

本脚手架作为工程规范的一部分，对工程分包和编码规范提供了样例工程，相关的一些规范在样例工程中提供了规范的实现，供开发者参考。


## 本存在的意义

- 减少工程创建时大量的重复工作
- 统一代码风格，规范中提出了统一的编码规范，以减少研发之间相互之间的代码理解成本
- 提供开发样例
- 对一些spring-boot没有提供starter的中间件，进行starter的实现并引入，实现自动组件的自动配置

## 规范中默认约束的组件和版本

| 组件 | 版本 |
|---|---|
|  spring-boot |2.1.7.RELEASE|
|  jdk |1.8|
|  lombok |1.18.8|
|  gson |2.8.5|
|  jsf |1.6.9|


## 快速开始

**安装 Yeoman**

```
$ npm install -g yo
```

**安装 generator-unionstars**

```
$ npm install -g generator-unionstars
```

**初始化一个开箱即用的后端服务中心工程**

```
$ yo unionstars
```
_The interactive CLI menu will guide the way._


**初始化一个接口工程**

```
$ yo unionstars:api
```


**初始化一个前端网关工程**

```
$ yo unionstars:application
```

## TODO


## License

[MIT License](http://en.wikipedia.org/wiki/MIT_License)



