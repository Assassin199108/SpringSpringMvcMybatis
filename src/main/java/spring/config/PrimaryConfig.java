package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.basics.di.javaconfig.FunctionService;
import spring.basics.di.javaconfig.UseFunctionService;

/**
 * Created by wangwei on 2017/9/17.
 * Spring 配置类 主要的
 */
@Configuration
@ComponentScan(value = "spring.basics")
@EnableAspectJAutoProxy
public class PrimaryConfig {

    @Bean
    public FunctionService functionService(){
        return new FunctionService();
    }

    @Bean
    public UseFunctionService useFunctionService(){
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }

}
