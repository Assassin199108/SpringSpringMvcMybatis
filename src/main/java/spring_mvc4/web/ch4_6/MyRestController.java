package spring_mvc4.web.ch4_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring_mvc4.service.DemoService;

/**
 * Created by wangwei on 2017/10/4.
 */
@Controller
public class MyRestController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testRest(){
        return demoService.saySomething();
    }

}
