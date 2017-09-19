package spring.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangwei on 2017/9/17.
 */
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(MacOsCondition.class)
    public ListService macOsListService(){
        return new MacOsListService();
    }

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService(){
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService(){
        return new LinuxListService();
    }
}
