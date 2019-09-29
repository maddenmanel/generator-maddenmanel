package <%=packageName%>.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginViewController {

    private String rightUserName = "admin";

    private String rightPassword = "admin";

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (null == username || null == password) {
            return "redirect:/";
        }

        String md5info = rightUserName.toLowerCase() + rightPassword.toLowerCase();
        String realPassword = DigestUtils.md5DigestAsHex(md5info.getBytes());
        if (!password.equals(realPassword)) {
            return "redirect:/";
        }
        request.getSession().setAttribute("loginName", "admin");
        return "redirect:/welcome";
    }

    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}
