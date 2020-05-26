package <%=packageName%>.<%=baseName%>.web.gateway.jsf;

import com.jdd.jdpay.common.jsf.annotation.GateWay;
import com.jdd.jdpay.common.log.annotation.Loggable;
import com.jdd.jdpay.common.ump.annotation.UmpProfiler;
import com.jdd.jdpay.demo.api.Response;
import com.jdd.jdpay.demo.api.facade.UserServiceProvider;
import com.jdd.jdpay.demo.api.mo.UserMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@GateWay
public class UserServiceProviderImpl implements UserServiceProvider {

    @Override
    @Loggable
    @UmpProfiler
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
