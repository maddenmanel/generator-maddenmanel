package  <%=packageName%>.<%=baseName%>.gateway;

import com.jd.spring.boot.jsf.annotation.JsfProvider;
import com.jdd.splitaccount.clearing.api.Response;
import com.jdd.splitaccount.clearing.api.facade.UserServiceProvider;
import com.jdd.splitaccount.clearing.api.mo.UserMO;
import  <%=packageName%>.<%=baseName%>.common.annotation.GateWay;
import  <%=packageName%>.<%=baseName%>.domain.User;
import  <%=packageName%>.<%=baseName%>.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@JsfProvider(alias = "${jsf.provider.alias}")
@Slf4j
@Service
@GateWay
public class UserGateway implements UserServiceProvider {

    @Autowired
    private UserService userService;

    @Override
    public Response<UserMO> getOne(Integer integer) {
        Long id = Long.valueOf(integer);
        User user = userService.getOne(id);
        if (user == null) {
            Response.builder().success(false).data(null).message("user is not exist!");
        }
        UserMO userMO = convertUserToUserMO(user);
        Response<UserMO> response = Response.<UserMO>builder().success(true).data(userMO).build();
        return response;
    }

    private UserMO convertUserToUserMO(User user) {
        UserMO userMO = new UserMO();
        userMO.setId(user.getId());
        userMO.setName(user.getName());
        userMO.setAddr(user.getAddr());
        userMO.setAge(user.getAge());
        return userMO;
    }
}
