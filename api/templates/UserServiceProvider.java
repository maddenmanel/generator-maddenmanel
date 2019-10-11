package <%=packageName%>.<%=baseName%>.facade;

import <%=packageName%>.<%=baseName%>.Response;
import <%=packageName%>.<%=baseName%>.mo.UserMO;

/**
 * @author zhangxuegang@gmail.com
 */
public interface UserServiceProvider {

  Response<UserMO> getOne(Integer id);
}
