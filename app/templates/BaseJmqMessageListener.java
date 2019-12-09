package <%=packageName%>.<%=baseName%>.jmq.listener;

import com.jd.jmq.client.consumer.MessageListener;
import com.jd.jmq.common.message.Message;
import <%=packageName%>.<%=baseName%>..common.UMPCaller;
import <%=packageName%>.<%=baseName%>.constants.UmpConstants;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author zhangxuegang@jd.com
 */
public abstract class BaseJmqMessageListener<T> implements MessageListener {
  @Override
  public void onMessage(List<Message> messageList) {
    if (CollectionUtils.isEmpty(messageList)) {
      getLogger().warn("messageList is empty");
      return;
    }
    String destination = messageList.get(0).getTopic();
    for (Message message : messageList) {
      UMPCaller.callWithTp(UmpConstants.JMQ_CONSUMER + destination, () -> {
        //1. 过滤无用消息
        if (!usefulMessage(message)) {
          getLogger().info("message ignore, topic:{} businessId:{}", destination, message.getBusinessId());
          return;
        }

        //2. 将消息体转换为对应对象
        T t = UMPCaller.callWithTp(UmpConstants.JMQ_CONSUMER + UmpConstants
          .CONVERTER + destination, () -> messageToObject(message));

        //3 过滤无用消息
        if (!usefulMessage(t)) {
          getLogger().warn("message ignore,topic:{} businessId:{}", destination, message.getBusinessId());
          return;
        }

        //4. 处理消息
        UMPCaller.callWithTp(UmpConstants.JMQ_CONSUMER + UmpConstants
          .SAVE + destination, () -> handleMessage(message, t));

        getLogger().info("message type:{}, consume success, businessId:{}", destination, message
          .getBusinessId());
      });
    }
  }

  /**
   * 判断此消息是否需要消费
   *
   * @param message JMS消息实体
   * @return 是否需要消费
   */
  protected abstract boolean usefulMessage(Message message);

  /**
   * 判断此消息是否需要消费
   *
   * @param t
   * @return
   */
  protected boolean usefulMessage(T t) {
    return true;
  }

  /**
   * 消息反序列化
   *
   * @param message 订单消息
   * @return 反序列化对象
   */
  protected abstract T messageToObject(Message message);

  /**
   * 处理拼装message
   *
   * @param message 订单消息
   */
  protected abstract void handleMessage(Message message, T t);

  protected abstract Logger getLogger();
}
