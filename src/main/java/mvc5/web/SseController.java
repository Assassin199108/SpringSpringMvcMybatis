package mvc5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by wangwei on 2017/10/3.
 * 服务器端推送一演示
 */
@Controller
public class SseController {

    /**
     * 输出媒体的类型为text/event-stream，这是服务器端SSE的支持，本例演示每5秒钟向浏览器推送随机消息
     * @return
     */
    @RequestMapping(value = "/push",produces = "text/event-stream")
    @ResponseBody
    public String push(){
        Random r = new Random();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "data:Testing 1,2,3"+r.nextInt() + "\n\n";
    }

}
