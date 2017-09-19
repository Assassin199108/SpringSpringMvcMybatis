package spring.ch2.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by wangwei on 2017/9/17.
 * spring自定义事件
 */
public class DemoEvent extends ApplicationEvent {

    private String msg;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
