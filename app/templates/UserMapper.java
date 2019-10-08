package <%=packageName%>.dao;

import <%=packageName%>.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(User user);

}
