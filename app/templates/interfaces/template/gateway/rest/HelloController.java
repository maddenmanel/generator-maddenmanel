package <%=packageName%>.<%=baseName%>.interfaces.gateway.rest;

import <%=packageName%>.<%=baseName%>.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/hi")
    public String user(Integer id) {
        return userService.getUser(id);
    }
}
