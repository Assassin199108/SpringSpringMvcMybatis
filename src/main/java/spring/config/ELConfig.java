package spring.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * Created by wangwei on 2017/9/17.
 * 属性注入配置类
 */
@Configuration
@ComponentScan("spring.ch2.el")
@PropertySource("classpath:spring/ch2/el/test.properties")
public class ELConfig {

    @Value("I Love You!")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{ T(java.lang.Math).random()*100.0}")
    private double randomNumber;

    @Value("#{demoELService.another}")
    private String fromAnother;

    @Value("classpath:spring/ch2/el/test.txt")
    private Resource testFile;

    @Value("http://www.baidu.com")
    private Resource testUrl;

    @Value(value = "${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource(){

        try {
         System.out.println(normal);
         System.out.println(osName);
         System.out.println(randomNumber);
         System.out.println(fromAnother);

         System.out.println(IOUtils.toString(testFile.getInputStream()));
         System.out.println(IOUtils.toString(testUrl.getInputStream()));
         System.out.println(bookName);
         System.out.println(environment.getProperty("book.autor"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
