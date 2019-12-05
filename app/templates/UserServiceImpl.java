package <%=packageName%>.<%=baseName%>.service.impl;

import <%=packageName%>.<%=baseName%>.dao.UserMapper;
import <%=packageName%>.<%=baseName%>.domain.User;
import <%=packageName%>.<%=baseName%>.service.UserService;
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

  @Override
  @Cacheable(value = "<%= systemName %>", key = "'getOne_' + #id", unless = "#result == null")
  public User getOne(Long id){
    return userMapper.getOne(id);
  }

}
