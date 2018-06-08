package spring.basics.di.service;

import org.springframework.stereotype.Service;

/**
 * Created by wangwei on 2017/9/17.
 */
@Service
public class FunctionService {

    public String sayHello(String word){
        return "hello"+word+"!";
    }

}
