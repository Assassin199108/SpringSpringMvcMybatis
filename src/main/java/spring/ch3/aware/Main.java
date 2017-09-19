package spring.ch3.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangwei on 2017/9/17.
 * spring容器 服务测试类
 */
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareConfig.class);
        context.refresh();

        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();

        context.close();
    }

}
