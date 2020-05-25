package <%=packageName%>.<%=baseName%>.service.biz;

import com.jdd.jdpay.common.log.annotation.Loggable;
import com.jdd.jdpay.common.ump.annotation.UmpProfiler;
import com.jdd.jdpay.demo.api.Response;
import com.jdd.jdpay.demo.api.facade.UserServiceProvider;
import com.jdd.jdpay.demo.api.mo.UserMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserServiceProvider userServiceProvider;

    @Loggable
    @UmpProfiler
    public String getUser(Integer id) {
        Response<UserMO> userMOResponse = userServiceProvider.getOne(id);
        return "hello," + userMOResponse.getData().getName();
    }
}
