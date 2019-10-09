package <%=packageName%>.<%=baseName%>.service;

import <%=packageName%>.<%=baseName%>.BaseTest;
import <%=packageName%>.<%=baseName%>.domain.User;
import <%=packageName%>.<%=baseName%>.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

  @Autowired
  private UserService userService;

  @Test
  public void insert() {
    User user = new User();
    user.setId(1);
    user.setAge(18);
    user.setName("Mac");
    user.setAddr("Ameria");
    userService.insert(user);
  }
}
