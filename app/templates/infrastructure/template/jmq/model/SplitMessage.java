package <%=packageName%>.<%=baseName%>.infrastructure.jmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitMessage {
    private long tenantId;
    private long orderId;
}
