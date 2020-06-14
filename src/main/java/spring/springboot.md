> # Springboot

> Spring Boot的主要优点

    1.开发基于 Spring 的应用程序很容易。
    2.Spring Boot 项目所需的开发或工程时间明显减少，通常会提高整体生产力。
    3.Spring Boot不需要编写大量样板代码、XML配置和注释。
    4.Spring引导应用程序可以很容易地与Spring生态系统集成，如Spring JDBC、Spring ORM、Spring Data、Spring Security等。
    5.Spring Boot遵循“固执己见的默认配置”，以减少开发工作（默认配置可以修改）。
    6.Spring Boot 应用程序提供嵌入式HTTP服务器，如Tomcat和Jetty，可以轻松地开发和测试web应用程序。（这点很赞！普通运行Java程序的方式就能运行基于Spring Boot web 项目，省事很多）
    7.Spring Boot提供命令行接口(CLI)工具，用于开发和测试Spring Boot应用程序，如Java或Groovy。
    8.Spring Boot提供了多种插件，可以使用内置工具(如Maven和Gradle)开发和测试Spring Boot应用程序。
    
> @SpringBootApplication注解

    @SpringBootApplication 看作是 @Configuration、@EnableAutoConfiguration、@ComponentScan 注解的集合。
    @EnableAutoConfiguration：启用 SpringBoot 的自动配置机制
    @ComponentScan： 扫描被@Component (@Service,@Controller)注解的bean，注解默认会扫描该类所在的包下所有的类。
    @Configuration：允许在上下文中注册额外的bean或导入其他配置类
    
> Spring Boot的自动配置

    @EnableAutoConfiguration是启动自动配置的关键
    
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @AutoConfigurationPackage
    @Import({AutoConfigurationImportSelector.class})
    public @interface EnableAutoConfiguration {
        String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
    
        Class<?>[] exclude() default {};
    
        String[] excludeName() default {};
    }
    
    @EnableAutoConfiguration注解通过Spring提供的@Import注解导入了AutoConfigurationImportSelector类
    AutoConfigurationImportSelector类中getCandidateConfigurations方法会将所有自动配置类的信息以List的形式返回。这些配置信息会被Spring容器作bean来管理。
    
