package common.anno;

import java.lang.annotation.*;

/**
 * 不安全线程
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface UnSafeThread {
}
