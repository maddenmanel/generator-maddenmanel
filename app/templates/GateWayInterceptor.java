package <%=packageName%>.<%=baseName%>.interceptor;

import <%=packageName%>.<%=baseName%>.common.GateWayExceptionConvert;
import <%=packageName%>.<%=baseName%>.common.IGateWayFinally;
import <%=packageName%>.<%=baseName%>.common.ITraceIdFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @author zhangxuegang@jd.com
 */
public class GateWayInterceptor implements MethodInterceptor{

    private final GateWayExceptionConvert gateWayExceptionConvert;

    private ITraceIdFactory traceIdFactory;

    private IGateWayFinally gateWayFinally;

    public GateWayInterceptor(GateWayExceptionConvert gateWayExceptionConvert) {
        this.gateWayExceptionConvert = gateWayExceptionConvert;
    }

    public void setITraceIdFactory(ITraceIdFactory traceIdFactory) {
        this.traceIdFactory = traceIdFactory;
    }

    public void setGateWayFinally(IGateWayFinally gateWayFinally) {
        this.gateWayFinally = gateWayFinally;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String traceId = null;
        try {
            if (traceIdFactory != null) {
                traceId = traceIdFactory.traceId();
            }
            return invocation.proceed();
        } catch (Exception e) {
            Object result = gateWayExceptionConvert.convert(traceId, e);
            if (gateWayExceptionConvert.isException()) {
                //noinspection ConstantConditions
                throw (Throwable) result;
            } else {
                return result;
            }
        } finally {
            if (gateWayFinally != null) {
                gateWayFinally.doSth();
            }
        }
    }
}
