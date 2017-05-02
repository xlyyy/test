# spring-threadpool-demo
## 介绍：基于spring框架的spring线程池、springAOP、springIOC的模拟示例

### 1：spring线程池：
#### Main类：该类负责读取配置文件、创建线程池对象（ThreadPoolTaskExecutor）、线程对象（PrintTask）并在线程池中启动。<br>直接运行该类的main方法，没有编写测试类
#### PrintTask类：创建线程的类，并用sleep模拟线程的执行时间。
#### application.xml配置文件：配置了线程池，定义了核心线程数，最大线程数和缓冲队列数。

### 2：springAOP：
#### SpringAop类：定义了切面（find（）方法和findByName（）方法）和通知（前置和后置）。
#### AOPTest类:测试类。
#### application.xml配置文件：定义了代理类SpringAop和被代理类SpringServiceImpl

### 3：springIOC:
##### 在demo中随处都有体现，包括配置文件中创建Bean容器，在Main类的main方法中、测试方法中通过容器获取实例等。
