package <%=packageName%>.<%=baseName%>.jmq.producer;

import <%=packageName%>.<%=baseName%>.BaseTest;
import <%=packageName%>.<%=baseName%>.common.GsonUtil;
import <%=packageName%>.<%=baseName%>.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class JmqProducerTest extends BaseTest {

  @Autowired
  private JmqProducer jmqProducer;

  private String topic = "test";

  @Test
  public void testSend() {
    User user = new User();
    user.setId(2L);
    user.setName("zhangsan");
    jmqProducer.send(user.getId(), topic, GsonUtil.objectToJsonStr(user));
  }
}
