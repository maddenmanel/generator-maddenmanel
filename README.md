![logo](logo/Gen-Spring-Logo.jpg?raw=true) 

# generator-spring [![Build Status](https://travis-ci.org/davetownsend/generator-spring.svg?branch=master)](https://travis-ci.org/davetownsend/generator-spring)

A [Yeoman](http://yeoman.io) generator for scaffolding and bootstrapping [Spring Boot](http://projects.spring.io/spring-boot/) and [Spring Cloud](http://projects.spring.io/spring-cloud/) applications. Provides the same selectable options as [Spring Initializr](http://start.spring.io), but with and interactive CLI interface so your hands can stay where they belong, on the keyboard!

## Getting Started

### 适用对象

本脚手架作为工程规范的一部分，对工程分包和编码规范提供了样例工程，相关的一些规范在样例工程中提供了规范的实现，供开发者参考。


### 本存在的意义

- 减少工程创建时大量的重复工作
- 统一代码风格，规范中提出了统一的编码规范，以减少研发之间相互之间的代码理解成本
- 提供开发样例
- 对一些springboot没有提供starter的中间件，进行starter的实现并引入，实现自动组件的自动配置


### 脚手架的使用
**Install Yeoman**

```
$ npm install -g yo
```

**Install generator-spring**

```
$ npm install -g generator-spring
```

**Initiate spring-generator!**

```
$ yo spring
```
_The interactive CLI menu will guide the way._


### Extras (Sub-generators)
Creates a REST endpoint

```
$ yo spring:rest
```

Creates a REST endpoint with HATEOAS support *(has dependency on choosing the hateoas starter option)*

```
$ yo spring:hateoas
```

Creates an initial Thymeleaf view *(has dependency on choosing the Thyemleaf starter option)*

```
$ yo spring:route
```

## TODO
- Add the new Spring Cloud libs supported with Spring Boot 1.5.x
- Add support for createing a serverless (AWS) project with Spring Cloud Function. 
- Add React support. This will essentially scaffold an opinionated react SPA (webpack/es6/material-ui) as the front end.

## License

[MIT License](http://en.wikipedia.org/wiki/MIT_License)
