package com.web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spring_mvc4.MyMvcConfig;
import spring_mvc4.filter.MyFilterTest;

import javax.servlet.*;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.*;

/**
 * Created by wangwei on 2017/9/17.
 * servlet的配置 代替Web.xml位置的 用来启动servlet3.0容器
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 注册初始变量
     * 注册dispatcherSerlvet
     * 注册filter
     * 注册新的servlet
     * 注册listener 监听器
     *
     * 填充servlet内容
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        String servletName = getServletName();
        logger.info("得到本次Web容器服务的Servlet名字："+servletName);

        servletContext.setInitParameter("spring.profiles.default","dev");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);

        context.setServletContext(servletContext);
        //设置一个SpringMVC的DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);


        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",dispatcherServlet);
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        servletContext.setAttribute("initMsg","my name is wang");//设置servlet全局参数

        FilterRegistration.Dynamic filter = servletContext.addFilter("test", MyFilterTest.class);

        filter.setInitParameter("my first Filter","MyFilterTest");
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/");
        filter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),true,"SpringMvcFilter");
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * 注册servlet上下文参数
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setAsyncSupported(isAsyncSupported());
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

/**
     * 设置WebMVC  DispatcherServlet的参数主要用于抛出404
     * @param servletContext
     */
    /*@Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        String servletName = getServletName();
        logger.info("得到本次Web容器服务的Servlet名字："+servletName);
        Assert.hasLength(servletName, "getServletName() may not return empty or null");

        //Web服务的应用上下文
        WebApplicationContext servletApplicationContext = createServletApplicationContext();
        Assert.notNull(servletApplicationContext,
                "createServletApplicationContext() did not return an application " +
                        "context for servlet [" + servletName + "]");

        //我的应用上下文
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(servletApplicationContext.getServletContext());
        context.register(MyMvcConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        //重点！！！！这里设置
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

    }*/
}
