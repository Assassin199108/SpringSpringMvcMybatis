package spring.ch3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.ch3.springTest.TestBean;
import spring.ch3.springTest.TestConfig;

/**
 * Created by wangwei on 2017/9/18.
 * Spring 首次测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)//在Junit环境下提供Soring testContext Framework功能
@ContextConfiguration(classes = {TestConfig.class})//用来加载配置类
@ActiveProfiles("prod")
public class DemoBeanIntegrationTests {

    @Autowired
    private TestBean testBean;

    @Test
    public void devBeanShouldInject(){
        String expetcd = "from development profile";
        String actual = testBean.getContent();
        Assert.assertEquals(expetcd,actual);
    }


}
