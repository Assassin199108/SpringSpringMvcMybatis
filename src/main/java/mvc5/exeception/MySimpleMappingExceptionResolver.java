package mvc5.exeception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangwei on 2017/9/28.
 * handler异常处理
 * @see: MyHandlerExceptionResolver
 */
public class MySimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    private static Log log = LogFactory.getLog(MySimpleMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("我已经进入自己实现的handlerMapping异常处理类中-------------->MySimpleMappingExceptionResolver");

        ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
        modelAndView.addObject("url2",request.getRequestURL());

        return modelAndView;
    }
}
