package <%=packageName%>.<%=baseName%>.infrastructure.jmq;

import com.jd.jmq.common.message.Message;
import com.jdd.jdpay.common.jmq.BaseJmqMessageListener;
import com.jdd.jdpay.common.json.JacksonUtil;
import <%=packageName%>.<%=baseName%>.infrastructure.jmq.model.SplitMessage;
import com.jdd.jdpay.spring.boot.autoconfigure.jmq.JMQListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
@JMQListener("splitOrderMessageListener")
public class SplitOrderMessageListener extends BaseJmqMessageListener<SplitMessage> {
    @Override
    protected boolean usefulMessage(Message message) {
        return true;
    }

    @Override
    protected SplitMessage messageToObject(Message message) {
        return JacksonUtil.jsonToObject(message.getText(), SplitMessage.class);
    }

    @Override
    protected void handleMessage(Message message, SplitMessage splitMessage) {
    }

    @Override
    protected Logger getLogger() {
        return log;
    }
}
