package spring_mvc4.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wangwei on 2017/9/28.
 * 自定义的filter
 */
public class MyFilterTest implements Filter {

    private static Log log = LogFactory.getLog(MyFilterTest.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化filter--------------------->MyFilterTest");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("MyFilterTest开始过滤前---------------->IP地址:"+request.getRemoteAddr());
        chain.doFilter(request,response);
        log.info("MyFilterTest过滤后------------------->地址:"+response.getLocale().getCountry());
    }

    @Override
    public void destroy() {
        log.info("关闭filter-------------------------->MyFilterTest");
    }
}
