package spring.basics.aop;

import org.springframework.stereotype.Service;

/**
 * Created by wangwei on 2017/9/17.
 * 编写使用方法规则拦截类
 */
@Service
public class DemoMethodService {

    public void add(){}

    /**
     * 方法拦截参数并对参数修改后进行输出
     * @param word
     */
    public void sayHello(String word){
        System.out.println("Hello "+word+"!");
    }

}
