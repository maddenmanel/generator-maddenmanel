package <%=packageName%>.<%=baseName%>.gateway;

import com.jdd.splitaccount.clearing.api.Response;
import com.jdd.splitaccount.clearing.api.facade.UserServiceProvider;
import com.jdd.splitaccount.clearing.api.mo.UserMO;
import <%=packageName%>.<%=baseName%>.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserGatewayTest extends BaseTest {
    @Autowired
    private UserServiceProvider userServiceProvider;

    @Test
    public void testGetOne() {
        Integer id = 1;
        Response<UserMO>  response = userServiceProvider.getOne(id);
        assertTrue(response.isSuccess());
    }
}
