package mvc5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import mvc5.domain.DemoObj;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangwei on 2017/9/19.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody String index(HttpServletRequest request){
        return "url:"+request.getRequestURL()+"can access";
    }

    @RequestMapping(value = "/requestParam/{str}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar (@PathVariable String str, HttpServletRequest request){
        return "url:"+request.getRequestURL()+"can access,str:"+str;
    }

    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id , HttpServletRequest request){
        return "url:"+request.getRequestURL() + "can access,id"+id;
    }

    @RequestMapping(value = "/obj",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String passObj(DemoObj obj,HttpServletRequest request){
        return "url:"+request.getRequestURL()+"can access,obj id:"+obj.getId()+"obj name:"+obj.getName();
    }

    @RequestMapping(value = {"/name","/name2"},produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(HttpServletRequest request){
        return "url:"+request.getRequestURL()+"can access";
    }

}
