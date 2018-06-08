package mvc5;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import common.anno.Excloud;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.dao.DataAccessException;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import mvc5.exeception.MySimpleMappingExceptionResolver;
import mvc5.config.resolver.httpMessage.MyMessageConverter;
import mvc5.interceptor.DemoInterceptor;
import mvc5.kit.BeanKit;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 *
 * @author wangwei
 * @date 2017/9/18
 * Spring mvc配置类
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("mvc5")
public class MyMvcConfig extends WebMvcConfigurerAdapter{

    public MyMvcConfig() {
        super();
    }


    /**
     * 自动扫描相关的参数解析器并且注册
     *
     * @param argumentResolvers 在给定请求的上下文中，将方法参数解析为参数值的策略接口
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.addAll(BeanKit.getListOfType(HandlerMethodArgumentResolver.class));
    }

    /**
     * 自动扫描相关组件并且注册
     * 参数转换器注册
     *
     * @param registry FormatterRegistry 转换器注册
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //循环在这个注册表中添加一个普通的转换器。
        for (Converter converter : BeanKit.getListOfType(org.springframework.core.convert.converter.Converter.class)) {
            registry.addConverter(converter);
        }
        //循环在这个注册表中添加一个通用转换器。
        for (GenericConverter genericConverter : BeanKit.getListOfType(GenericConverter.class)) {
            registry.addConverter(genericConverter);
        }
        //循环添加一个格式化程序来格式化特定类型的字段。添加一个格式化程序来格式化特定类型的字段。
        for (Formatter formatter : BeanKit.getListOfType(Formatter.class)) {
            registry.addFormatter(formatter);
        }
    }

    /**
     * 页面导向使用:不需要再添加controller添加重定向页面
     * @param registry ViewControllerRegistry
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
     * @return DemoInterceptor
     */
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    /**
     * 声明自己的文件上传
     * @return MultipartResolver
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 自定义的全局handlermapping一些异常的默认处理
     * @return SimpleMappingExceptionResolver
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
     * 1:添加FastJson消息转换器
     * 2:添加自定义application/x-wisely媒体类型的请求体数据
     * 3:添加字符串消息转换器 编码
     *
     * @param converters 消息转换器集合
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //结果能否被格式化
                SerializerFeature.PrettyFormat,
                //是否输出Null字段
                SerializerFeature.WriteMapNullValue,
                //字符类型字段如果为null,输出为”“
                SerializerFeature.WriteNullStringAsEmpty,
                //List字段如果为null,输出为[]
                SerializerFeature.WriteNullListAsEmpty,
                //消除对同一对象循环引用的问题
                SerializerFeature.DisableCircularReferenceDetect);

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        //1
        converters.add(fastJsonHttpMessageConverter);

        //2
        converters.add(converter());

        //3
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
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
     *
     * @param: registry 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(demoInterceptor());
        //更高端的写法
        BeanKit.getListOfType(HandlerInterceptor.class).forEach(handlerInterceptor -> {
            val registration  = registry.addInterceptor(handlerInterceptor);
            val exclude = handlerInterceptor.getClass().getAnnotation(Excloud.class);
            if (Objects.nonNull(exclude)) {
                Arrays.stream(exclude.value()).forEach(registration::excludePathPatterns);
            }
        });
    }
}
