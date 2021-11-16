package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String show(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public UserT login(String name,String password){
        UserT userBean = userService.loginIn(name,password);
        if(userBean!=null){
            UserT user=new UserT();
            user.setPassword(password);
            user.setName(name);
            return user;
        }
        return null;
    }
}
