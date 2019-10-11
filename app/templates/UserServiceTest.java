package <%=packageName%>.<%=baseName%>.service;

import <%=packageName%>.<%=baseName%>.BaseTest;
import <%=packageName%>.<%=baseName%>.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

  @Test
  public void getOne() {
    Integer id = 1;
    User user = userService.getOne(id);
    assertNotNull(user);
    assertEquals(id, user.getId());
  }
}

