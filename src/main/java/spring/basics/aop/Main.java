package spring.basics.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.PrimaryConfig;

/**
 * Created by wangwei on 2017/9/17.
 */
public class Main {

    public static void main(String args[]){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrimaryConfig.class);

        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);

        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);

        demoAnnotationService.add();

        demoMethodService.add();

        demoMethodService.sayHello("world");

        context.close();
    }

}
