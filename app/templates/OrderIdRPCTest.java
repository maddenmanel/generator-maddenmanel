package <%=packageName%>.<%=baseName%>.rpc;

import <%=packageName%>.<%=baseName%>.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class OrderIdRPCTest extends BaseTest {

    @Autowired
    private OrderIdRPC orderIdRPC;

    @Test
    public void testGenerateOrderId() {
        Long orderId = orderIdRPC.generateOrderId();
        assertNotNull(orderId);
    }
}
