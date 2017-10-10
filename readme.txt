tips：


day09一：Spring MVC测试
        测试Web项目不需要启动项目：需要servlet相关的模拟对象,比如：
        MockMVC,MockHttpServletRequest,MockHttpServletResponse,MockHttpSession
        引入一个概念：测试驱动开发(TDD)
        借助Junit和Spring TestContext framework 和RestController

day08一：监听器的作用：                  监听对象                        监听对象的属性
        1：统计在线人数和在线用户       HttpSessionListener           httpSessionAttributeListener
        2：系统启动时加载初始化信息      servletContentListener       ServletContextAttributeListener
        3：统计网站访问量               ServletRequestListenr        ServletRequestAttributeListener
        4：跟Spring结合             例如:servletContentListener     ServletContextAttributeListener

     二：HttpMessageConverter是用来处理request和response里的数据的
        extendMessageConverters
            配置自定义的HttpMessageConverter的Bean，在Spring MVC注册HttpMessageConverter的2个方法
            仅添加自定义的HttpMessageConverter，不覆盖默认注册的仅添加自定义的HttpMessageConverter

        configureMessageConverters:
            重载会覆盖掉SpringMvc默认注册的多个HttpMessageConverter

     三：媒体类型text/event-stream是服务器端SSE的支持

day07一：
        Spring MVC 内置默认的request作用域所存在的上下文全部的参数 例如：
        集合1参数为:org.springframework.web.context.request.async.WebAsyncManager.WEB_ASYNC_MANAGER
        集合2参数为:org.springframework.web.servlet.DispatcherServlet.CONTEXT
        集合3参数为:org.springframework.web.servlet.DispatcherServlet.LOCALE_RESOLVER
        集合4参数为:org.springframework.web.servlet.HandlerMapping.bestMatchingPattern
        集合5参数为:org.springframework.web.servlet.DispatcherServlet.OUTPUT_FLASH_MAP
        集合6参数为:org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping
        集合7参数为:startTime
        集合8参数为:org.springframework.web.servlet.DispatcherServlet.FLASH_MAP_MANAGER
        集合9参数为:org.springframework.web.servlet.HandlerMapping.uriTemplateVariables
        集合10参数为:org.springframework.web.servlet.DispatcherServlet.THEME_RESOLVER
        集合11参数为:org.springframework.web.servlet.DispatcherServlet.THEME_SOURCE

     二：RequestContextListener类的作用：
        需要先了解ContextLoaderListener，此监听器将Web容器与Spring容器整合为什么这里还要用额外的RequestContextListener
        以支持Bean的另外3个作用域，原因是ContextLoaderListener实现ServletContextListener监听器接口，
        而ServletContextListener只负责监听Web容器的启动和关闭的事件

        RequestContextFilter实现ServletRequestListener监听器接口，该监听器监听HTTP请求事件，Web服务器接收的每次请求都会通知该监听器。通过配置RequestContextFilter，Spring容器与Web容器结合的更加密切

        并且设定RequestContextHolder中的requestAttributesHolder

        RequestContextHolder类的作用:
        RequestContextHolder顾名思义,持有上下文的Request容器.可以在Service层获取request
        没有进入handlerMapping就无法获取到此参数




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

