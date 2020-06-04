package <%=packageName%>.<%=baseName%>.service.biz;

import com.jdd.jdpay.common.easyuse.annotation.EasyServiceMethod;
import com.jdd.jdpay.demo.api.Response;
import com.jdd.jdpay.demo.api.facade.UserServiceProvider;
import com.jdd.jdpay.demo.api.mo.UserMO;
import <%=packageName%>.<%=baseName%>.domain.User;
import <%=packageName%>.<%=baseName%>.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserServiceProvider userServiceProvider;

    @Autowired
    private UserMapper userMapper;

    @EasyServiceMethod
    public String getUserWithJSF(Integer id) {
        Response<UserMO> userMOResponse = userServiceProvider.getOne(id);
        return "hello," + userMOResponse.getData().getName();
    }

    @EasyServiceMethod
    public String getUserWithDB(Integer id) {
        User user = userMapper.getOne(Long.valueOf(id));
        return "hello," + user.getName();
    }
}
