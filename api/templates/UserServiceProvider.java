package <%=packageName%>.<%=baseName%>.facade;

import <%=packageName%>.<%=baseName%>.Response;
import <%=packageName%>.<%=baseName%>.dto.UserDTO;

/**
 * @author zhangxuegang@gmail.com
 */
public interface UserServiceProvider {

  Response<UserDTO> getOne(Integer id);
}
