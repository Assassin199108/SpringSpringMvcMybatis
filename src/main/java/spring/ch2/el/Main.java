package spring.ch2.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.ELConfig;

/**
 * Created by wangwei on 2017/9/17.
 * 表达式测试
 */
public class Main {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ELConfig.class);

        ELConfig elConfig = context.getBean(ELConfig.class);
        elConfig.outputResource();

        context.close();

    }

}
