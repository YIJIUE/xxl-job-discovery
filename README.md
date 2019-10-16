# xxl-job-discovery

#### 介绍
基于xxl-job改造服务注册中心实现服务自动发现的分布式定时任务

#### 编写背景
> 为解决公司项目微服务拆分下带来的netty端口管理问题，采用基于注册中心实现的自服务发现进行调度。目前已支持eureka，下一版本支持nacos

#### 软件架构
![image](./doc/images/xxl-job-discovery.png)


#### 软件环境
````
1. JDK1.8+
2. springboot1.5x+ | springboot2.x+
````
#### 使用说明
1. 导入数据库sql
2. 根据需要修改对应properties属性
3. 输入网页地址 `http://ip:port` 

![image](./doc/images/使用步骤.png)

4. 根据需要修改测试用例属性 将示例执行器启动

![image](./doc/images/示例应用.png)

5. 调度中心新增执行器 30s内`执行器`会自动发现`调度器`

![image](./doc/images/新增执行器.png)

##
感谢原作者 `xuxueli`


