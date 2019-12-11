package <%=packageName%>.<%=baseName%>.configration;

import <%=packageName%>.<%=baseName%>.common.ITraceIdFactory;
import <%=packageName%>.<%=baseName%>.common.MDCFinally;
import <%=packageName%>.<%=baseName%>.common.TraceIdFactory;
import <%=packageName%>.<%=baseName%>.common.annotation.GateWay;
import <%=packageName%>.<%=baseName%>.common.annotation.Loggable;
import <%=packageName%>.<%=baseName%>.exception.BizExceptionConvert;
import <%=packageName%>.<%=baseName%>.interceptor.GateWayInterceptor;
import <%=packageName%>.<%=baseName%>.interceptor.LoggableInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class EasyUseConfig {

    @Bean
    public DefaultPointcutAdvisor loggableAnnotationClassPointCut() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setOrder(Ordered.HIGHEST_PRECEDENCE + 500 * 2);
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Loggable.class, true);
        LoggableInterceptor interceptor = new LoggableInterceptor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

    @Bean
    public DefaultPointcutAdvisor gateWayAnnotationClassPointCut() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setOrder(Ordered.HIGHEST_PRECEDENCE);
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(GateWay.class, true);
        GateWayInterceptor interceptor = new GateWayInterceptor(bizExceptionConvert());
        interceptor.setITraceIdFactory(traceIdFactory());
        interceptor.setGateWayFinally(mdcFinally());
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

    @Bean
    public BizExceptionConvert bizExceptionConvert() {
        return new BizExceptionConvert();
    }

    @Bean
    public ITraceIdFactory traceIdFactory() {
        return new TraceIdFactory();
    }


    @Bean
    public MDCFinally mdcFinally() {
        return new MDCFinally();
    }


}
