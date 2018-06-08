package spring.basics.di.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.PrimaryConfig;

/**
 * Created by wangwei on 2017/9/17.
 */
public class main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrimaryConfig.class);

        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);

        System.out.println(useFunctionService.sayHello("java config"));

        context.close();
    }

}
