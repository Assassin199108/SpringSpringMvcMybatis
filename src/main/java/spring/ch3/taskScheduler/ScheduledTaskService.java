package spring.ch3.taskScheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangwei on 2017/9/17.
 * 定时任务
 */
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)//每隔5秒钟执行一次
    public void reportCurrentTime(){
        System.out.println("每隔5秒钟执行一次："+DATE_FORMAT.format(new Date()));
    }

    @Scheduled(cron = "0 28 11 ? * *")//每天11点28分执行
    public void fixTimeExecution(){
        System.out.println("在指定时间"+DATE_FORMAT.format(new Date())+"执行");
    }

}
