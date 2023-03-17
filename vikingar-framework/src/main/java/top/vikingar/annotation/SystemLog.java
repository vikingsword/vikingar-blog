package top.vikingar.annotation;


import java.lang.annotation.*;

/**
 * @author vikingar
 * Aop
 * @time 2023/3/17 17:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface SystemLog {

    String BusinessName();
}
