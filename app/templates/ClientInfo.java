package <%=packageName%>.<%=baseName%>.common;

import lombok.Data;

/**
 * @author zhangxuegang@jd.com
 */
@Data
public class ClientInfo {
    private String appId;
    private String appName;
    private String appInsId;
    private String ip;
    private String traceId;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ClientInfo clientInfo = new ClientInfo();

        public Builder appId(String appId) {
            clientInfo.setAppId(appId);
            return this;
        }

        public Builder appName(String appName) {
            clientInfo.setAppName(appName);
            return this;
        }

        public Builder appInsId(String appInsId) {
            clientInfo.setAppInsId(appInsId);
            return this;
        }

        public Builder ip(String ip) {
            clientInfo.setIp(ip);
            return this;
        }

        public Builder traceId(String traceId) {
            clientInfo.setTraceId(traceId);
            return this;
        }

        public ClientInfo build() {
            return clientInfo;
        }
    }

}
