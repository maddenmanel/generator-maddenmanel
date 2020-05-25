package <%=packageName%>.<%=baseName%>.service.biz;

import <%=packageName%>.<%=baseName%>.service.biz.UserService;
import <%=packageName%>.<%=baseName%>.start.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void hello() {
        String result = userService.getUserWithDB(1);
        assertNotNull(result);
    }
}
