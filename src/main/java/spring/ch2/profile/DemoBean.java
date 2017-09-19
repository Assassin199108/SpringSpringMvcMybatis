package spring.ch2.profile;

/**
 * Created by wangwei on 2017/9/17.
 */
public class DemoBean {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DemoBean(String content) {

        this.content = content;
    }
}
