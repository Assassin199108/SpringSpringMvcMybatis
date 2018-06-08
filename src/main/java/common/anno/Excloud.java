package common.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Excloud {

    /**
     * @return 排除的路径
     */
    String[] value();

}
