package spring_mvc4.exeception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangwei on 2017/9/21.
 * 全局异常处理
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception e, WebRequest request){
        System.out.println("什么是webRequest"+request);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage",e.getMessage());
        return modelAndView;
    }

    @ModelAttribute//将键值添加到全局，所有注解@RequestMapping的方法都可获得此键值对
    public void addAttrbutes(Model model){
        model.addAttribute("msg","额外的信息");
    }

    @InitBinder//用于设置自动绑定前台请求参数到model中
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");//此处演示忽略request参数的id
        webDataBinder.setFieldDefaultPrefix("nam");
    }

}
