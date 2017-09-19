package spring.ch3.taskScheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by wangwei on 2017/9/17.
 */
@Configuration
@ComponentScan
@EnableScheduling//开启对计划任务的支持
public class TaskSchedulerConfig {
}
