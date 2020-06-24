package spring;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLifeCycle implements InitializingBean, DisposableBean {

    // (将要被废弃)
    // 用来修饰方法, 标记在项目启动的时候执行这个方法, 一般用来执行某些初始化操作比如全局配置。
    // PostConstruct 注解的方法会在构造函数之后执行, Servlet的init()方法之前执行。
    @PostConstruct
    public void afterInit() {

    }

    // (将要被废弃)
    // 当bean被Web容器的时候被调用, 一般用来释放bean所持有的资源。
    // PreDestroy 注解的方法会在Servlet的destroy()方法之前执行。
    @PreDestroy
    public void preDestory() {

    }

    //类似PostConstruct 需要实现InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    //类似PreDestroy 需要实现DisposableBean
    @Override
    public void destroy() throws Exception {

    }
}
