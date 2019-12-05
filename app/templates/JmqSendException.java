package <%=packageName%>.<%=baseName%>.exception;


import com.jdd.common.util.MessageFormatter;

public class JmqSendException extends RuntimeException {

    private final String topic;
    private final String businessId;
    private final String text;

    public JmqSendException(Throwable cause, String topic, String businessId, String text) {
        super(cause);
        this.topic = topic;
        this.businessId = businessId;
        this.text = text;
    }

    @Override
    public String getMessage() {
        return MessageFormatter.format("topic:{} businessId:{} text:{} {}", topic, businessId, text, super.getMessage());
    }

    public String getTopic() {
        return topic;
    }

    public String getBusinessId() {
        return businessId;
    }

    public String getText() {
        return text;
    }
}
