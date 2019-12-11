package <%=packageName%>.<%=baseName%>.common;

import org.slf4j.MDC;

public class MDCFinally implements IGateWayFinally {
    @Override
    public void doSth() {
        // 清空MDC
        MDC.clear();
    }
}
