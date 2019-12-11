package <%=packageName%>.<%=baseName%>.common.annotation;

import java.lang.annotation.*;

/**
 * @author zhangxuegang@jd.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GateWay {
}
