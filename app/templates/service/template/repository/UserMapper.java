package <%=packageName%>.<%=baseName%>.service.repository;


import <%=packageName%>.<%=baseName%>.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(User user);

  User getOne(Long id);
}
