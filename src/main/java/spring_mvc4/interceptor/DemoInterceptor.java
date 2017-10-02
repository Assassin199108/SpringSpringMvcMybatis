package spring_mvc4.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created by wangwei on 2017/9/20.
 * 自定义的拦截器 用于拦截webMvc请求的上下文
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {

    private static Log log = LogFactory.getLog(DemoInterceptor.class);

    /**
     * 拦截前 所需要做的事前
     * 置初始化操作或者是对当前请求做一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去
     * @param: request
     * @param: response
     * @param: handler
     * @return: true 继续调用下一个 Interceptor 的 preHandle 方法，如果已经是最后一个 Interceptor 的时候，就会是调用当前请求的 Controller 中的方法
     * @return: false 请求结束，后续的 Interceptor 和 Controller 都不会再执行
     * @throws: Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Instant instant = Instant.now();
        request.setAttribute("startTime",instant);

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        log.info("在servlet启动前是否已经持有上下文的Request容器"+requestAttributes);

        String[] attrs = requestAttributes.getAttributeNames(RequestAttributes.SCOPE_SESSION);
        int count=0;
        for (String attr : attrs) {
            count++;
            log.info("得到"+RequestAttributes.SCOPE_SESSION+"作用域内全部的参数集合:集合"+count+"参数为:"+attr);
        }

        attrs = requestAttributes.getAttributeNames(RequestAttributes.SCOPE_REQUEST);
        count=0;
        for (String attr : attrs) {
            count++;
            log.info("得到"+RequestAttributes.REFERENCE_REQUEST+"作用域内全部的参数集合:集合"+count+"参数为:"+attr);
        }

        log.info("当前得到session作用域的请求参数："+requestAttributes.getAttribute("name", RequestAttributes.SCOPE_SESSION));
        HttpServletRequest request2 = ((ServletRequestAttributes)requestAttributes).getRequest();
        log.info("2次request是否相等"+request.equals(request2));

        System.out.println("拦截前！拦截到了本次请求的url地址为："+request.getRequestURL());
        return true;
    }

    /**
     * 拦截后 所需要做的事情
     *  Controller 中的方法调用之后执行，但是它会在 DispatcherServlet 进行视图返回渲染之前被调用
     *  在这个方法中对 Controller 处理之后的 ModelAndView 对象进行操作
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Instant startTime = (Instant) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        Instant endTime = Instant.now();
        Long duration = startTime.until(endTime, ChronoUnit.MILLIS);

        System.out.println("拦截后，拦截到了本次请求去往渲染页面的地址为："+modelAndView.getViewName());
        System.out.println("本次请求处理时间为1："+duration);

        request.setAttribute("handlingTime",duration);
    }

    /**
     * 拦截完成后
     * 在 DispatcherServlet 渲染了对应的视图之后执行，这个方法的主要作用是用于进行资源清理的工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("渲染页面后");
    }

    /**
     * 用于处理异步请求，当Controller中有异步请求方法的时候会触发该方法时，异步请求先支持preHandle、然后执行afterConcurrentHandlingStarted
     * 异步线程完成之后执行preHandle、postHandle、afterCompletion
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    public DemoInterceptor() {
        super();
    }
}