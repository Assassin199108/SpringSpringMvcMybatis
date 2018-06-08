package mvc5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import mvc5.domain.DemoObj;

/**
 * Created by wangwei on 2017/10/2.
 * 消息转化的控制层
 */
@Controller
public class ConverterController {

    @RequestMapping(value = "/convert",produces = {"application/x-wisely"})
    @ResponseBody
    public DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }

}
