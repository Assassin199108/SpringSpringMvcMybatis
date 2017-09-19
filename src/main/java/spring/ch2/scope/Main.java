package spring.ch2.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.Ch2Config;

/**
 * Created by wangwei on 2017/9/17.
 * 作用域测试
 */
public class Main {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Ch2Config.class);

        DemoSingletonService singletonService = context.getBean(DemoSingletonService.class);
        DemoSingletonService singletonService2 = context.getBean(DemoSingletonService.class);

        DemoPrototypeService prototypeService = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService prototypeService2 = context.getBean(DemoPrototypeService.class);

        System.out.println("单例2个是否相等"+singletonService.equals(singletonService2));

        System.out.println("多例2个是否相等"+prototypeService.equals(prototypeService2));

    }

}
