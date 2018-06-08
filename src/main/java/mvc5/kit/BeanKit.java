package mvc5.kit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BeanKit implements ApplicationContextAware  {

    private static ApplicationContext applicationContext;

    public BeanKit() {
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getOfName(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getOfType(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> Collection<T> getListOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz).values();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanKit.applicationContext = applicationContext;
    }



}
