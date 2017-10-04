package spring_mvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import spring_mvc4.exeception.MySimpleMappingExceptionResolver;
import spring_mvc4.httpMessage.MyMessageConverter;
import spring_mvc4.interceptor.DemoInterceptor;

import java.util.List;
import java.util.Properties;

/**
 * Created by wangwei on 2017/9/18.
 * Spring mvc配置类
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("spring_mvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter{

    public MyMvcConfig() {
        super();
    }


    /**
     * 页面导向使用:不需要再添加controller添加重定向页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index2").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //设置.后缀的参数不会取消
        configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    @Primary
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 声明自己的拦截器
     * @return
     */
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    /**
     * 声明自己的文件上传
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 自定义的全局handlermapping一些异常的默认处理
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new MySimpleMappingExceptionResolver();

        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        Properties properties = new Properties();
        properties.setProperty(NoHandlerFoundException.class.getName(),"exception/404");
        properties.setProperty(DataAccessException.class.getName(),"exception/database");

        resolver.setExceptionMappings(properties);
        resolver.setDefaultErrorView("exception/404");
        resolver.setExceptionAttribute("exception");
        resolver.setWarnLogCategory(getClass().getName());

        return resolver;

    }

    /**
     * 配置自定义的HttpMessageConverter的Bean，在Spring MVC注册HttpMessageConverter的2个方法
     * 仅添加自定义的HttpMessageConverter，不覆盖默认注册的仅添加自定义的HttpMessageConverter
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }

    /**
     *  重载会覆盖掉SpringMvc默认注册的多个HttpMessageConverter
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    /**
     * 不被拦截，对外暴露的地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations指的是文件放置的目录，addResourceHandler是对外暴露的访问路径
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        registry.addResourceHandler("/error/**").addResourceLocations("classpath:/error/");
    }



    /**
     * 新增拦截器  拦截器需要普通bean实现HanlderInterceptor接口或者继承HandlerInterceptorAdapter类来实现
     * @param: registry 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }
}
