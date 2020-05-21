package <%=packageName%>.<%=baseName%>.infrastructure.jmq;

import com.jdd.jdpay.common.jmq.JmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderJmqSender {

    @Autowired
    private JmqProducer producer;

    private String topic = "test";

    public void sendOrderXML(long orderId, String orderXML) {
        producer.send(orderId, topic, orderXML);
    }
}
