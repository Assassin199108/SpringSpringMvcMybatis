package spring.basics.aop;

import org.springframework.stereotype.Service;

/**
 * Created by wangwei on 2017/9/17.
 * 编写注解的被拦截类
 */
@Service
public class DemoAnnotationService {

    @Action(name = "注解式拦截的add操作")
    public void add(){}

}
