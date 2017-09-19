package spring.ch3.springTest;


import ch.qos.logback.core.ConsoleAppender;

/**
 * Created by wangwei on 2017/9/18.
 */
public class TestBean {

    private String content;

    public TestBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
