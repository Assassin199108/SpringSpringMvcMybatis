package spring.ch3.conditional;

/**
 * Created by wangwei on 2017/9/17.
 */
public class LinuxListService implements ListService {

    @Override
    public String showListCmd() {
        return "ls";
    }

}
