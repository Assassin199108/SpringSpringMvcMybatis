package spring_mvc4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import spring_mvc4.service.PushService;

/**
 * Created by wangwei on 2017/10/4.
 * 开启一个异步的控制层
 */
@Controller
public class AysncController {

    @Autowired
    PushService pushService;

    /**
     * 返回给客户端的DeferredResult
     *
     * @return
     */
    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall(){
        return pushService.getAsyncUpdate();
    }

}
