package <%=packageName%>.<%=baseName%>.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author zhangxuegang@jd.com
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyValid {
    @AliasFor("valid")
    boolean value() default true;

    @AliasFor("value")
    boolean valid() default true;
}
