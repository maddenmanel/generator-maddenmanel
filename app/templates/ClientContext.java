package <%=packageName%>.<%=baseName%>.common;


/**
 * @author zhangxuegang@jd.com
 */
public final class ClientContext {

    private static final ThreadLocal<ClientContext> CLIENT_INFO_CONTEXT = ThreadLocal.withInitial(ClientContext::new);

    private ClientInfo clientInfo;

    public static ClientContext getContext() {
        return CLIENT_INFO_CONTEXT.get();
    }

    public static String traceId() {
        return ClientContext.getContext().getClientInfo() == null ? null : ClientContext.getContext().getClientInfo().getTraceId();
    }

    public static void removeContext() {
        CLIENT_INFO_CONTEXT.remove();
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }
}
