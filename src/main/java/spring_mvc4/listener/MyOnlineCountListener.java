package spring_mvc4.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by wangwei on 2017/10/1.
 * 统计当前在线用户人数
 */
public class MyOnlineCountListener implements HttpSessionListener {

    private static Log log = LogFactory.getLog(MyOnlineCountListener.class);

    public MyOnlineCountListener() {
        log.info("在线用户人数统计监听器初始化");
    }

    /**
     * 监听到当有一个session创建的时候
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("onlineCount");
        if (onlineCount==null)
            servletContext.setAttribute("onlineCount",1);
        else {
            onlineCount++;
            servletContext.setAttribute("onlineCount",onlineCount);
        }
    }

    /**
     * 监听到当有一个session关闭的时候
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("onlineCount");
        if (onlineCount == null)
            servletContext.setAttribute("onlineCount",1);
        else{
            onlineCount--;
            servletContext.setAttribute("onlineCount",onlineCount);
        }
    }
}
