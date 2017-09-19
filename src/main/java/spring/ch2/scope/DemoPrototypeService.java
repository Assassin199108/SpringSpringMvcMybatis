package spring.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by wangwei on 2017/9/17.
 * 每次调用一个bean 新建一个bean对象
 */
@Service
@Scope(value = "prototype")
public class DemoPrototypeService {


}
