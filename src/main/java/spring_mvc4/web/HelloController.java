package spring_mvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**`
 * Created by wangwei on 2017/9/18.
 * 我的第一个controller
 */
@RequestMapping("/")
@Controller
public class HelloController {

    @RequestMapping("index")
    public String hello(HttpServletRequest request){
        //servletContext
        ServletContext servletContext = request.getServletContext();
        Object attribute = servletContext.getAttribute("initMsg");
        System.out.println(attribute.toString());

        return "index";
    }

}
