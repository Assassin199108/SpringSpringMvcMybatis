package spring_mvc4.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDateTime;

/**
 * Created by wangwei on 2017/10/4.
 * 定时任务
 */
@Service
public class PushService {

    //当有一个长时间运行的任务时，这是特别有趣的，因为当另一个线程处理这个请求时，容器线程被释放，并且可以继续为其他请求服务
    private DeferredResult<String> deferredResult;

    public DeferredResult<String> getAsyncUpdate(){
        deferredResult = new DeferredResult<>();
        return deferredResult;
    }

    //定时更新DeferredResult
    @Scheduled(fixedDelay = 5000)
    public void refresh(){
        if (deferredResult != null){
            LocalDateTime localDateTime = LocalDateTime.now();

            deferredResult.setResult(localDateTime.toString());
        }
    }

}
