package spring.ch2.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.PrePostConfig;

/**
 * Created by wangwei on 2017/9/17.
 */
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);

        //BeanWayService beanWayService = context.getBean(BeanWayService.class);

        //JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);

        context.close();

    }

}
