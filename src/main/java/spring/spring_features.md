> # Spring

> 模块
    
    Spring Core: 核心库,Spring 其他所有的功能都需要依赖于该类库。主要提供IoC依赖注入功能。
    Spring Aspects: 该模块为与AspectJ的集成提供支持。
    Spring AOP: 提供了面向切面的编程实现。
    Spring JDBC: Java数据库连接。
    Spring JMS: Java消息服务。
    Spring ORM: 用于支持Hibernate等ORM工具。
    Spring Web: 为创建Web应用程序提供支持。
    Spring Test: 提供了对 JUnit 和 TestNG 测试的支持。
    
> IOC (Inverse of Control 控制反转)

    将原本在程序中手动创建对象的控制权，交由Spring框架来管理。
    IoC容器是Spring用来实现IoC的载体，IoC容器实际上就是个Map（key，value）,Map中存放的是各种对象。key为beanId, 即Bean的唯一标识符。
    IoC采用了工厂模式(BeanFactory)。当我们需要创建对象的时候，只需要配置好配置文件/注解，IOC就会帮我们创建好具体的对象。
    所谓依赖注入，就是把底层类作为参数传入上层类，实现上层类对下层类的“控制”。
    
> AOP (spect-Oriented Programming 面向切面编程)

    将那些与业务无关，却为业务模块所共同调用的逻辑或责任(例如事务处理、日志管理、权限控制等)封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。
    
> Spring bean

    作用域
        singleton: 唯一 bean 实例，Spring中的bean默认都是单例的。
        prototype: 每次请求都会创建一个新的bean实例。
        request: 每一次HTTP请求都会产生一个新的bean，该bean仅在当前HTTP request内有效。
        session: 每一次HTTP请求都会产生一个新的bean，该bean仅在当前 HTTP session 内有效。

    注解
        @Component: 通用的注解，可标注任意类为Spring组件。如果一个Bean不知道属于哪个层，可以使用@Component 注解标注。
        @Repository: 对应持久层即Dao 层，主要用于数据库相关操作。
        @Service: 对应服务层，主要涉及一些复杂的逻辑，需要用到Dao层。
        @Controller: 对应Spring MVC控制层，主要用户接受用户请求并调用Service 层返回数据给前端页面。
        @RestController: 对应Spring MVC控制层，restful接口的bean注解
        

        