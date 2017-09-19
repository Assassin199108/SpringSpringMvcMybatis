package spring.ch1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by wangwei on 2017/9/17.
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(spring.ch1.aop.Action)")
    public void annotationPointCut(){};

    @Pointcut("execution(* spring.ch1.aop.DemoMethodService.*(String))")
    public void replaceParemeters(){};

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);

        System.out.println("注解拦截"+action.name());

    }

    @Before("execution(* spring.ch1.aop.DemoMethodService.*())")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截,"+method.getName());
    }

    @Around("replaceParemeters()")
    public void surround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("拦截前");
        if (String.class.isInstance(args[0])){
            String oldString = (String) args[0];
            System.out.println("被拦截方法的参数"+oldString);
            oldString = oldString + " AOP IS SO Amazing";
            args[0] = oldString;
            joinPoint.proceed(args);
        }else {
            System.out.println("没有拦截到方法参数");
            joinPoint.proceed();
        }

        System.out.println("拦截后");
    }

}
