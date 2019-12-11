package <%=packageName%>.<%=baseName%>.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * @author zhangxuegang@jd.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {
  /**
   * logger category name,if value is empty ,it will get class default
   * @return
   */
  @AliasFor("loggerName")
  String value() default "";

  @AliasFor("value")
  String loggerName() default "";

  boolean enabled() default true;

  /**
   * 方法的入参和返回的时候参数的日志级别,默认为Debug,error的日志依然是 error 不受此设置影响。
   * @return
   */
  Level argsLogLevel() default Level.DEBUG;

  enum Level{
    DEBUG,
    INFO;
  }
}
