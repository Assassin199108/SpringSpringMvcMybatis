package spring.ch3.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by wangwei on 2017/9/17.
 */
@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i){
        System.out.println("当前线程："+Thread.currentThread().getName()+"执行异步任务:"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("当前线程："+Thread.currentThread().getName()+"执行异步任务+1："+(i+1));
    }

}
