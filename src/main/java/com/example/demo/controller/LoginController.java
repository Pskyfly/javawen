package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    UserService userService;
    @RequestMapping("/login")
    public String show(){
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String name,String password){
        UserT userBean = userService.loginIn(name,password);
        if(userBean!=null){
            return "content";
        }else {
            return "welcome";
        }
    }
}
