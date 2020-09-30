 下面解析 ControllerAdvice 和 ExceptionHandler 的底层代码：

1. Controller层抛出错误，然后进入 DispatcherServlet 的 processHandlerException方法
    > org.springframework.web.servlet.DispatcherServlet

2. DispatcherServlet 中对异常捕获 (转换成RuntimeException) ，然后进入 HandlerExceptionResolverComposite 的 resolveException 方法
    > org.springframework.web.servlet.handler.HandlerExceptionResolverComposite
  
3. HandlerExceptionResolverComposite 调用到具体的 AbstractHandlerExceptionResolver 的 resolveException 方法
    > org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver 

4. AbstractHandlerExceptionResolver 抽象类继续深入，调用到 ExceptionHandlerExceptionResolver 的 doResolveHandlerMethodException 方法 
    > org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver

5. ExceptionHandlerExceptionResolver 会去调用自己内部的 getExceptionHandlerMethod 方法，以获取有 ExceptionHandler注解 的具体方法

6. 然后执行具体有 ExceptionHandler注解 的方法

7. 从ExceptionHandler出来后，进入到 InvocableHandlerMethod 类
    > org.springframework.web.method.support.InvocableHandlerMethod

8. InvocableHandlerMethod 类后进入到 ServletInvocableHandlerMethod 类的 invokeAndHandle 
方法
    > org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod

9. ServletInvocableHandlerMethod 执行完后返回，进入 HandlerMethodReturnValueHandlerComposite 类
    > org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite

10. HandlerMethodReturnValueHandlerComposite 返回后，进入到 HttpEntityMethodProcessor 类的 handleReturnValue 方法
    > org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor

11. HttpEntityMethodProcessor 类返回后，进入到 AbstractMessageConverterMethodProcessor 类的 writeWithMessageConverters 方法
    > org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor

12. 之后就没往下跟了，反正大概就是 ExceptionHandler 处理后将返回放到 HttpEntity 中，继续往下传递时，
    只需要 HttpEntity 中的 body 也就是你在 ExceptionHandler 中往 HttpEntity 放的数据，
    之后会把这个 body 放入HttpResponse 的 body 里面，然后进行渲染返回。