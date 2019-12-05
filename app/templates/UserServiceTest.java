package <%=packageName%>.<%=baseName%>.service;

import <%=packageName%>.<%=baseName%>.BaseTest;
import <%=packageName%>.<%=baseName%>.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceTest extends BaseTest {

  @Autowired
  private UserService userService;

  @Test
  public void insert() {
    User user = new User();
    user.setId(1L);
    user.setAge(18);
    user.setName("Mac");
    user.setAddr("Ameria");
    user.setCreated(new Date());
    user.setModified(new Date());
    userService.insert(user);
  }

  @Test
  public void getOne() {
    Long id = 1L;
    User user = userService.getOne(id);
    assertNotNull(user);
    assertEquals(id, user.getId());
  }
}

