package spring_mvc4.domain;

/**
 * Created by wangwei on 2017/9/19.
 */
public class DemoObj {

    private Long id;

    private String name;

    //jackson对对象和json转换时 一定需要此空构造
    public DemoObj() {
    }

    public DemoObj(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
