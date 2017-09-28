tips：
day06一：新增自己的HandlerExceptionResolver的作用类继承与SimpleMappingExceptionResolver
        因未找到对应的controller中的mapping 并且在dispatcherServlet中声明抛出异常
        被此截获，加入到ioc容器bean中管理
        声明默认的一些异常处理的页面

     二：新增自己的filter
        并注册到servlet上下文中

day05 HandlerExceptionResolver的作用：执行过程中遇到异常处理方式
        本人自定义的异常处理 MyHandlerExceptionResolver
      对handler的了解
      通过HandlerMapping，将请求映射到处理器（返回一个HandlerExecutionChain，它包括一个处理器、多个HandlerInterceptor拦截器）

      对dispatcherServlet的理解
      一：HttpServletBean继承HttpServlet，因此在Web容器启动时将调用它的init方法，该初始化方法的主要作用
      将Servlet初始化参数（init-param）设置到该组件上（如contextAttribute、contextClass、namespace、contextConfigLocation），通过BeanWrapper简化设值过程，方便后续使用

      二：
      FrameworkServlet继承HttpServletBean，通过initServletBean()进行Web上下文初始化，该方法主要覆盖一下两件事情：
        1、初始化web上下文；
        2、提供给子类初始化扩展点

      三：
      DispatcherServlet继承FrameworkServlet，并实现了onRefresh()方法提供一些前端控制器相关的配置：
      DispatcherServlet启动时会进行我们需要的Web层Bean的配置，如HandlerMapping、HandlerAdapter等，而且如果我们没有配置，还会给我们提供默认的配置
        A：DispatcherServlet默认配置
        DispatcherServlet的默认配置在DispatcherServlet.properties（和DispatcherServlet类在一个包下）中，而且是当Spring配置文件中没有指定配置时使用的默认策略



day04 servletContext对象 可以由request获取

day03 webServlet由实现WebApplicationInitializer升级为继承AbstractAnnotationConfigDispatcherServletInitializer
        重写里面的Servlet方法，实现DispatcherSerlver一些功能

