package spring.ch3.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangwei on 2017/9/17.
 * Spring 多线程测试
 */
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext content = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

        AsyncTaskService taskService = content.getBean(AsyncTaskService.class);

        for (int i = 0; i < 10; i++) {
            taskService.executeAsyncTask(i);
            taskService.executeAsyncTaskPlus(i);
        }


        content.close();
    }

}
