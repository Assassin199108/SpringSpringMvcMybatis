package spring_mvc4.httpMessage;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import spring_mvc4.domain.DemoObj;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by wangwei on 2017/10/2.
 * 自定义处理request 和 response的数据
 * 并在Spring MVC中完成注册
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter() {
        //新建一个application/x-wisely的媒体类型
        super(new MediaType("application","x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * 表面本HttpMessageConvert只处理DemoObj这个类
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz);
    }

    /**
     * 得到由application/x-wisely媒体类型的请求体数据
     * 并进行处理
     * @param clazz
     * @param inputMessage
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));

        //我们处理由-隔开的数据
        String[] tempArray = temp.split("-");

        //把请求体转成DemoObj对象
        return new DemoObj(new Long(tempArray[0]),tempArray[1]);
    }

    /**
     * 输出数据到response,可以修改数据
     *
     * @param obj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:"+obj.getId()+"-"+obj.getName();

        outputMessage.getBody().write(out.getBytes());
    }
}
