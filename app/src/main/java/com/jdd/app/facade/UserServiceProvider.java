package com.jdd.app.facade;

import com.jdd.app.Response;
import com.jdd.app.dto.UserDTO;

/**
 * @author zhangxuegang@gmail.com
 */
public interface UserServiceProvider {

  Response<UserDTO> getOne(Integer id);
}
