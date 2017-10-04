package spring.spring_mvc4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring_mvc4.MyMvcConfig;
import spring_mvc4.service.DemoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by wangwei on 2017/10/4.
 * Spring mvc测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources")//注解在类似，用户来声明加载的，它的属性指定的是Web资源位置
public class TestControllerIntegrationTests {

    private MockMvc mockMvc;//模拟MVC对象，通过MockMVCBuilders.webAppContextSetup(this.wac).build()初始化

    @Autowired
    private DemoService demoService;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    MockHttpSession mockHttpSession;

    @Autowired
    MockHttpServletRequest request;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testNormalController() throws Exception{

        //模拟向/normal进行get请求
        mockMvc.perform(get("/normal"))
                //预期控制返回状态为200
                .andExpect(status().isOk())
                //预期view的名称为page
                .andExpect(view().name("page"))
                //预期的页面转向的真正路径为/WEB-INF/classes/views/page.jsp
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/classes/views/page.jsp"))
                //预期的model里的值是'hello'
                .andExpect(MockMvcResultMatchers.model().attribute("msg",demoService.saySomething()));
    }

    @Test
    public void testRestController() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/testRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //预期返回的媒体类型为text/plain;charset=UTF-8
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(demoService.saySomething()));

    }

}
