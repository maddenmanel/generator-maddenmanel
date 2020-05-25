package <%=packageName%>.<%=baseName%>.service.biz;

import com.jdd.jdpay.common.log.annotation.Loggable;
import com.jdd.jdpay.common.ump.annotation.UmpProfiler;
import com.jdd.jdpay.demo.api.Response;
import com.jdd.jdpay.demo.api.facade.UserServiceProvider;
import com.jdd.jdpay.demo.api.mo.UserMO;
import <%=packageName%>.<%=baseName%>.domain.User;
import <%=packageName%>.<%=baseName%>.service.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserServiceProvider userServiceProvider;

    @Autowired
    private UserMapper userMapper;

    @Loggable
    @UmpProfiler
    public String getUserWithJSF(Integer id) {
        Response<UserMO> userMOResponse = userServiceProvider.getOne(id);
        return "hello," + userMOResponse.getData().getName();
    }

    @Loggable
    @UmpProfiler
    public String getUserWithDB(Integer id) {
        User user = userMapper.getOne(Long.valueOf(id));
        return "hello," + user.getName();
    }
}
