package spring.basics.di.javaconfig;

/**
 * Created by wangwei on 2017/9/17.
 */
public class UseFunctionService {

    private FunctionService functionService;

    public String sayHello(String word){
        return  functionService.sayHello(word);
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }
}
