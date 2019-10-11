package <%=packageName%>.<%=baseName%>.dao;

import <%=packageName%>.<%=baseName%>.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(User user);

  User getOne(Integer id);
}
