package <%=packageName%>.service.impl;

import <%=packageName%>.dao.UserMapper;
import <%=packageName%>.domain.User;
import com.jdd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public void insert(User user) {
    userMapper.insert(user);
  }
}
