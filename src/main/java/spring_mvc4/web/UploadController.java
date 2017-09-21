package spring_mvc4.web;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by wangwei on 2017/9/21.
 * 上传文件控制器演示
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file, HttpServletRequest request){
        try {
            System.out.println("请求的编码格式："+request.getCharacterEncoding());
            System.out.println("前台文件："+new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8"));

            FileUtils.writeByteArrayToFile(new File("/Users/wangwei/Documents/gitRepositiry/WebRoot"+file.getOriginalFilename()),file.getBytes());
            return "ok";
        }catch (IOException e){
            e.printStackTrace();
            return "wrong";
        }
    }

}
