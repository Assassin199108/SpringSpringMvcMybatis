package spring_mvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**`
 * Created by wangwei on 2017/9/18.
 * 我的第一个controller
 */
@RequestMapping("/")
@Controller
public class HelloController {

    @RequestMapping("index")
    public String hello(){
        return "index";
    }

}
