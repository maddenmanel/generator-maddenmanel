package <%=packageName%>.<%=baseName%>.interceptor;

import <%=packageName%>.<%=baseName%>.common.AnnotatedElementCacheUtils;
import <%=packageName%>.<%=baseName%>.common.JacksonUtil;
import <%=packageName%>.<%=baseName%>.common.annotation.Loggable;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class LoggableInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Logger proxyLogger;
        Loggable loggable = AnnotatedElementCacheUtils.getMergedAnnotation(invocation.getThis().getClass(), Loggable.class);
        if (loggable == null || !loggable.enabled()) {
            return invocation.proceed();
        }
        String methodName = invocation.getMethod().getName();
        if (StringUtils.isEmpty(loggable.value())) {
            proxyLogger = LoggerFactory.getLogger(invocation.getThis().getClass());
        } else {
            proxyLogger = LoggerFactory.getLogger(loggable.value());
        }

        String argsJson = null;

        try {

            if (loggable.argsLogLevel().equals(Loggable.Level.INFO)) {
                if (proxyLogger.isInfoEnabled()) {
                    argsJson = JacksonUtil.writeValueAsString(invocation.getArguments());
                    proxyLogger.info("before invoke, method:{}, args:{}", methodName, argsJson);
                }
            } else {
                if (proxyLogger.isDebugEnabled()) {
                    argsJson = JacksonUtil.writeValueAsString(invocation.getArguments());
                    proxyLogger.debug("before invoke, method:{}, args:{}", methodName, argsJson);
                }
            }

            Object result = invocation.proceed();

            if (loggable.argsLogLevel().equals(Loggable.Level.INFO)) {
                if (proxyLogger.isInfoEnabled()) {
                    argsJson = JacksonUtil.writeValueAsString(invocation.getArguments());
                    String resultJson = JacksonUtil.writeValueAsString(result);
                    proxyLogger.info("after invoke, method:{}, args:{}, result:{}", methodName, argsJson,resultJson);
                }
            } else {
                if (proxyLogger.isDebugEnabled()) {
                    argsJson = JacksonUtil.writeValueAsString(invocation.getArguments());
                    String resultJson = JacksonUtil.writeValueAsString(result);
                    proxyLogger.debug("after invoke, method:{}, args:{} ,result:{}", methodName, argsJson,resultJson);
                }
            }
            return result;
        } catch (Throwable cause) {
            if (argsJson == null) {
                argsJson = JacksonUtil.writeValueAsString(invocation.getArguments());
            }
            proxyLogger.error("invoke fail, method:{}, args:{}", methodName, argsJson, cause);
            throw cause;
        }
    }

}
