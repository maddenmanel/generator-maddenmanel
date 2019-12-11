package <%=packageName%>.<%=baseName%>.common;

import com.jd.jsf.gd.config.annotation.AutoActive;
import com.jd.jsf.gd.config.annotation.Extensible;
import com.jd.jsf.gd.filter.AbstractFilter;
import com.jd.jsf.gd.msg.RequestMessage;
import com.jd.jsf.gd.msg.ResponseMessage;
import com.jd.jsf.gd.util.Constants;
import com.jd.jsf.gd.util.RpcContext;
import com.jd.jsf.gd.util.StringUtils;
import  <%=packageName%>.<%=baseName%>.constants.TraceConstants;

/**
 * @author zhangxuegang@jd.com
 */
@Extensible(value = "jsfClientContextFilter")
@AutoActive(providerSide = true, consumerSide = false)
public class JsfClientContextFilter extends AbstractFilter {
    @Override
    public ResponseMessage invoke(RequestMessage request) {
        try {
            if (RpcContext.getContext().isProviderSide()) {
                RpcContext rpcContext = RpcContext.getContext();
                String traceId = (String) rpcContext.getAttachment(TraceConstants.SIS_TRACK_ID);
                if (StringUtils.isBlank(traceId)) {
                    traceId = UUIDUtil.uuid();
                    rpcContext.setAttachment(TraceConstants.SIS_TRACK_ID, traceId);
                }
                ClientContext clientContext = ClientContext.getContext();
                clientContext.setClientInfo(
                        ClientInfo.builder()
                                .traceId(traceId)
                                .ip(rpcContext.getRemoteHostName())
                                .appId((String) rpcContext.getAttachment(Constants.HIDDEN_KEY_APPID))
                                .appName((String) rpcContext.getAttachment(Constants.HIDDEN_KEY_APPNAME))
                                .appInsId((String) rpcContext.getAttachment(Constants.HIDDEN_KEY_APPINSID))
                                .build()
                );
            }
            return this.getNext().invoke(request);
        } finally {
            ClientContext.removeContext();
        }
    }
}
