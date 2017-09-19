package spring.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangwei on 2017/9/17.
 * 事件监听器 专门监听实现了applicationEvent事件
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();

        System.out.println("我(demo-Listener)接受到了bean0demoPublisher发布的消息:"+msg);
    }

}
