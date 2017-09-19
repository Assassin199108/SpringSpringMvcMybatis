package spring.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangwei on 2017/9/17.
 */
public class Main {

   public static void main(String[] args){
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

       context.getEnvironment().setActiveProfiles("prod");
       context.register(ProfileConfig.class);
       context.refresh();

       DemoBean demoBean = context.getBean(DemoBean.class);
       System.out.println(demoBean.getContent());

       context.close();
   }


}
