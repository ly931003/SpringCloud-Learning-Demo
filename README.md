# SpringCloud-Learning-Demo

## 微服务

服务间通过轻量级通信机制互相协作，基于http协议的RESTful API。独立部署小模块



架构：

注册中心：Eureka、Zookeeper、Consul、Nacos

服务调用：Ribbon、LoadBalance、~~Feign~~、OpenFeign

降级熔断：Hystrix、resilience4j、Sentinel

服务网关：~~Zuul~~、GateWay

服务配置：Apolo、Nacos

服务总线：Nacos





## Zookeeper

版本不一致需要排除jar包

```xml
        <!-- SpringBoot整合zookeeper客户端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper3.5.3-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.9版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.9</version>
        </dependency>
```

zknode

微服务是临时节点，CP

## Consul



## Ribbon

客户端负载均衡

Nginx服务端负载均衡，转发请求到服务。Ribbon本地负载均衡，进程内负载均衡

负载均衡算法

* RoundRobin 轮询
* RandomRobin 随机
* RetryRobin 先轮询，失败重试
* WeightedResponseTime 响应时间权重轮询
* BestAvailable 过滤多次故障的服务，选取并发量最小的服务
* AvailabilityFiltering 过滤故障的服务，选取并发量最小的服务
* ZoneAvoidance 复合判断server所在区域和可用性

负载均衡配置需要在包扫描路径中排除。

