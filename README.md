# spring-system

本项目是一个基于 Spring Boot、Spring Cloud和 Spring Cloud Netflix 等框架构建的微服务项目。

# 技术栈
* Spring boot - 微服务的入门级微框架，用来简化 Spring 应用的初始搭建以及开发过程。
* Eureka - 云端服务发现，一个基于 REST 的服务，用于定位服务，以实现云端中间层服务发现和故障转移。
* Spring Cloud Config - 配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git 以及 Subversion。
* Hystrix - 熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。
* Zuul - Zuul 是在云平台上提供动态路由，监控，弹性，安全等边缘服务的框架。Zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。
* Spring Cloud Bus - 事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与 Spring Cloud Config 联合实现热部署。
* Ribbon - 提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。
* Turbine - Turbine 是聚合服务器发送事件流数据的一个工具，用来监控集群下 hystrix 的 metrics 情况。
* Spring Cloud Stream - Spring 数据流操作开发包，封装了与 Redis、Rabbit、Kafka 等发送接收消息。
* Feign - Feign 是一种声明式、模板化的 HTTP 客户端。

base-api  基础公共库

base-cms  cms管理后台

    (9) -- cms-service cms的后台服务
    
    (8)-- cms-web cms的前台页面
    
base-sso  统一登录权限管理中心

    (6)--sso-auth-service 登录和权限的后台服务
    
    (7) --sso-auth-web  登录和权限的前台页面
    
(2)service-config spring 配置中心


(1)service-eureka 注册中心


(5)service-gateways 路由中心


(3)service-monitor 监控中心


(4)service-zipkin 分布式跟踪中心
# lexue-cms
