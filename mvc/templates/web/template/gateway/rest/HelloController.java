package <%=packageName%>.<%=baseName%>.web.gateway.rest;

import <%=packageName%>.<%=baseName%>.service.biz.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/hi")
    public String user(Integer id) {
        String userNameWithDB = userService.getUserWithDB(id);
        String userNameWithJSF = userService.getUserWithJSF(id);
        return userNameWithDB + ";" + userNameWithJSF;
    }
}
