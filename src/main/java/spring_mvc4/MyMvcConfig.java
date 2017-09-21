package spring_mvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import spring_mvc4.interceptor.DemoInterceptor;

/**
 * Created by wangwei on 2017/9/18.
 * Spring mvc配置类
 */
@Configuration
@EnableWebMvc
@ComponentScan("spring_mvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 页面导向使用:不需要再添加controller添加重定向页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index2").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
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

    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations指的是文件放置的目录，addResourceHandler是对外暴露的访问路径
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
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
