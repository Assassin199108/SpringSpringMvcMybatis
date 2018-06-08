package common.anno;

import java.lang.annotation.*;

/**
 * 线程安全的
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SafeThread {
}
