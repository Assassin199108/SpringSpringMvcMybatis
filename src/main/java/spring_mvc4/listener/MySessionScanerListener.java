package spring_mvc4.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spring_mvc4.schedule.InvalidSessionTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

/**
 * Created by wangwei on 2017/10/2.
 * 自定义session扫描器
 */
public class MySessionScanerListener implements HttpSessionListener,ServletContextListener{

    private static Log log = LogFactory.getLog(MySessionScanerListener.class);

    public MySessionScanerListener() {
        log.info("session扫描的监听器初始化");
    }

/**
    * @Field: list
    *          定义一个集合存储服务器创建的HttpSession
    *        LinkedList不是一个线程安全的集合
    */
      /**
      * private List<HttpSession> list = new LinkedList<HttpSession>();
      * 这样写涉及到线程安全问题,SessionScanerListener对象在内存中只有一个
      * sessionCreated可能会被多个人同时调用，
      * 当有多个人并发访问站点时，服务器同时为这些并发访问的人创建session
      * 那么sessionCreated方法在某一时刻内会被几个线程同时调用，几个线程并发调用sessionCreated方法
      * sessionCreated方法的内部处理是往一个集合中添加创建好的session，那么在加session的时候就会
      * 涉及到几个Session同时抢夺集合中一个位置的情况，所以往集合中添加session时，一定要保证集合是线程安全的才行
      * 如何把一个集合做成线程安全的集合呢？
      * 可以使用使用 Collections.synchronizedList(List<T> list)方法将不是线程安全的list集合包装线程安全的list集合
      */
    //使用 Collections.synchronizedList(List<T> list)方法将LinkedList包装成一个线程安全的集合
    private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
    //定义一个对象，让这个对象充当一把锁，用这把锁来保证往list集合添加的新的session和遍历list集合中的session这两个操作达到同步
     private Object lock = new Object();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("web Servlet容器褚淑华");
        //创建定时器
        Timer timer = new Timer();
        //每隔30秒就定时执行任务
        timer.schedule(new InvalidSessionTask(list,lock),0,1000*30);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("web servlet容器关闭");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("有一个session被创建了");
        HttpSession session = se.getSession();

        /**
        *将该操作加锁进行锁定，当有一个thread-1(线程1)在调用这段代码时，会先拿到lock这把锁，然后往集合中添加session，
        *在添加session的这个过程中假设有另外一个thread-2(线程2)来访问了，thread-2可能是执行定时器任务的，
        *当thread-2要调用run方法遍历list集合中的session时，结果发现遍历list集合中的session的那段代码被锁住了，
        *而这把锁正在被往集合中添加session的那个thread-1占用着，因此thread-2只能等待thread-1操作完成之后才能够进行操作
        *当thread-1添加完session之后，就把lock放开了，此时thread-2拿到lock，就可以执行遍历list集合中的session的那段代码了
        *通过这把锁就保证了往集合中添加session和变量集合中的session这两步操作不能同时进行，必须按照先来后到的顺序来进行。
        */
        synchronized (lock){
            list.add(session);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session被销毁了");
    }
}
