package com.itheima.controller;

import com.itheima.domain.TbUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(TbUser user, HttpServletRequest request) {
        List<TbUser> tbUsers = userService.Login(user);
        if (tbUsers == null||tbUsers.size()==0) {
            request.setAttribute("error", "用户名或密码错误");
            return "login";
        } else if (tbUsers.size() > 1) {
            request.setAttribute("error", "信息有误，请及时联系管理员处理");
            return "login";
        }
        request.getSession().setAttribute("user",user);
        return "index";


    }
}
