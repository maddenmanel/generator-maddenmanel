package <%=packageName%>.<%=baseName%>.jmq.consumer;

import com.jd.jmq.common.message.Message;
import <%=packageName%>.<%=baseName%>.common.GsonUtil;
import <%=packageName%>.<%=baseName%>.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Slf4j
@Component("userAddListener")
public class UserAddListener extends BaseJmqMessageListener<User> {

  @Override
  protected boolean usefulMessage(Message message) {
    return true;
  }

  @Override
  protected User messageToObject(Message message) {
    return GsonUtil.jsonToObject(message.getText(), User.class);
  }

  @Override
  protected void handleMessage(Message message, User user) {
    log.info("get UserInfo: {}", GsonUtil.objectToJsonStr(user));
  }

  @Override
  protected Logger getLogger() {
    return log;
  }
}
