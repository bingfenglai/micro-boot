# micro-boot

这是Java语言微服务项目快速开发解决方案的后端部分，它源于我的毕业设计。

## 技术一览

微服务网关-- Spring Cloud Gateway

服务注册与发现-- Nacos Discovery

服务配置-- Nacos config

服务调用-- Dubbo @V2.7.8

## 服务划分说明

micro-common ---------------------公共的组件、可以快速集成的starter 

micro-api-------------------------服务内部调用的API声明(API总线)

micro-authentication--------------认证微服务 

micro-basic-support-services------基础微服务（业务边缘的服务）

micro-gateway---------------------微服务业务网关

······----------------------------未完待续。









