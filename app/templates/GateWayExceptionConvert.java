package <%=packageName%>.<%=baseName%>.common;


/**
 * @author zhangxuegang@jd.com
 */
public interface GateWayExceptionConvert<T> {

    boolean isException();

    T convert(String traceId, Exception e);
}
