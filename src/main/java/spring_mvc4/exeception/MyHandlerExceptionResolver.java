package spring_mvc4.exeception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangwei on 2017/9/22.
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    Log log = LogFactory.getLog(MyHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String exceUri = request.getRequestURI();
        log.error("异常的访问地址为："+exceUri);

        return null;
    }

}
