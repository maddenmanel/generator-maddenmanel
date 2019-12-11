package <%=packageName%>.<%=baseName%>.common;

import com.jd.jsf.gd.util.StringUtils;
import org.slf4j.MDC;

public class TraceIdFactory implements ITraceIdFactory {
    @Override
    public String traceId() {
        // 未来需要区分出来连接方式
        ClientInfo clientInfo = ClientContext.getContext().getClientInfo();
        String traceId = null;
        if (clientInfo != null) {
            traceId = clientInfo.getTraceId();

            addMdc("traceId", traceId);
            addMdc("ip", clientInfo.getIp());
            addMdc("appId", clientInfo.getAppId());
            addMdc("appName", clientInfo.getAppName());
            addMdc("appInsId", clientInfo.getAppInsId());
        }
        return traceId;
    }

    private static void addMdc(String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            MDC.put(key, value);
        }
    }
}
