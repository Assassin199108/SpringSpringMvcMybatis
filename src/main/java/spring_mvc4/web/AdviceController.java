package spring_mvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_mvc4.domain.DemoObj;

/**
 * Created by wangwei on 2017/9/21.
 */
@Controller
public class AdviceController {

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg")String msg, DemoObj obj){
        System.out.println("name:"+obj.getName());
        System.out.println("name:"+obj.getId());
        System.out.println("全局信息"+msg);

        throw new IllegalArgumentException("非常抱歉，参数有误/"+"来自@ModelAttribute:"+msg);
    }

}
