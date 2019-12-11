package <%=packageName%>.<%=baseName%>.rpc;

import com.jd.spring.boot.jsf.annotation.JsfConsumer;
import <%=packageName%>.<%=baseName%>.common.annotation.Loggable;
import com.xstore.cart.api.biz.enums.ChannelTypeEnum;
import com.xstore.cart.api.biz.service.OrderIdGenerateFacade;
import org.springframework.stereotype.Service;

@Service
@Loggable
public class OrderIdRPC {
    @JsfConsumer(alias = "${jsf.consumer.orderIdGenerateFacade.alias}",
            timeout = "${jsf.consumer.orderIdGenerateFacade.timeout}")
    private OrderIdGenerateFacade orderIdGenerateFacade;

    public Long generateOrderId() {
        return orderIdGenerateFacade.generateOrderId(ChannelTypeEnum.ON_LINE);
    }
}
