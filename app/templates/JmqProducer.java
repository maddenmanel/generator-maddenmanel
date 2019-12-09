package <%=packageName%>.<%=baseName%>.jmq.producer;

import com.jd.jmq.client.producer.Producer;
import com.jd.jmq.common.exception.JMQException;
import com.jd.jmq.common.message.Message;
import com.jdd.splitaccount.clearing.center.exception.JmqSendException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jdd.splitaccount.clearing.center.common.UMPCaller.callWithTp;

@Component
@Slf4j
public class JmqProducer {
    @Autowired
    private Producer producer;

    public void send(Object businessId, String topic, String text) {
        send(businessId, topic, text, true);
    }

    public void send(Object businessId, String topic, String text, boolean exceptionFlag) {
        callWithTp("<%=packageName%>.<%=baseName%>.jmq.send", ()->{
            try {
              producer.send(new Message(topic, text, businessId.toString()));
            } catch (JMQException e) {
                log.error("topic:{} businessId:{} text:{}", topic, businessId, text, e);
                if (exceptionFlag) {
                    throw new JmqSendException(e, topic, businessId.toString(), text);
                }
            }catch (Exception e) {
                log.error("topic:{} businessId:{} text:{}", topic, businessId, text, e);
                throw new JmqSendException(e, topic, businessId.toString(), text);
            }
        });
    }
}
