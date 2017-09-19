package spring.ch2.prepost;

import org.springframework.context.annotation.Lazy;

/**
 * Created by wangwei on 2017/9/17.
 */
public class BeanWayService {

    public void init(){
        System.out.println("@Bean-init-method");
    }

    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeamWayService");
    }


    public void destroy(){
        System.out.println("@Bean-destroy-method");
    }
}
