> # Spring MVC

> 工作原理:

    1.客户端(浏览器)发送请求，直接请求到DispatcherServlet。
    2.DispatcherServlet根据请求信息调用HandlerMapping，解析请求对应的Handler。
    3.解析到对应的Handler(也就是我们平常说的Controller控制器)后，开始由HandlerAdapter适配器处理。
    4.HandlerAdapter会根据Handler来调用真正的处理器开处理请求，并处理相应的业务逻辑。
    5.处理器处理完业务后，会返回一个ModelAndView对象，Model是返回的数据对象，View是个逻辑上的View。
    6.ViewResolver会根据逻辑View查找实际的View。
    7.DispaterServlet把返回的Model传给View(视图渲染)。
    8.把View返回给请求者(浏览器)
    
> SpringMVC组件说明

    1.前端控制器DispatcherServlet,由框架提供
        Spring MVC 的入口函数。接收请求，响应结果，相当于转发器。
        有了 DispatcherServlet 减少了其它组件之间的耦合度。用户请求到达前端控制器，它就相当于mvc模式中的c，DispatcherServlet是整个流程控制的中心，由它调用其它组件处理用户的请求，DispatcherServlet的存在降低了组件之间的耦合性。
    2.处理器映射器HandlerMapping,由框架提供
        根据请求的url查找Handler。
        HandlerMapping负责根据用户请求找到Handler即处理器(Controller)。
    3.处理器适配器HandlerAdapter
        按照特定规则去执行Handler 通过HandlerAdapter对处理器进行执行。
    4.处理器Handler
        具体的Controller
        编写Handler时按照HandlerAdapter的要求去做，这样适配器才可以去正确执行Handler
    5.视图解析器View resolver,由框架提供
        根据逻辑视图名解析成真正的视图(view)View Resolver负责将处理结果生成View视图
    6.视图View
        View是一个接口，实现类支持不同的View类型(jsp、freemarker、pdf...)
    
    