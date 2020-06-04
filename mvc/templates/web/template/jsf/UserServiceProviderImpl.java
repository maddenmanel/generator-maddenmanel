package <%=packageName%>.<%=baseName%>.web.jsf;

import com.jdd.jdpay.common.easyuse.annotation.EasyGateWay;
import com.jdd.jdpay.common.easyuse.annotation.EasyServiceMethod;
import com.jdd.jdpay.demo.api.Response;
import com.jdd.jdpay.demo.api.facade.UserServiceProvider;
import com.jdd.jdpay.demo.api.mo.UserMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@EasyGateWay
public class UserServiceProviderImpl implements UserServiceProvider {

    @Override
    @EasyServiceMethod
    @Cacheable(value = "demo-cache1", key = "'user_' + #name", unless = "#result == null")
    public Response<UserMO> getOne(Integer integer) {
        UserMO userMO = new UserMO();
        userMO.setId(1L);
        userMO.setName("fengqingyang");
        return Response.<UserMO>builder()
                .data(userMO)
                .build();
    }
}
