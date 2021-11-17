package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
@Controller
public class RegisterController {
    @Resource
    UserService userService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String show(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Object Login(UserT user,ModelMap modelMap){

        resultMap.clear();
        UserT auser = userService.findUserbynme(user.getName());
        if(auser!=null||user.getName()==null) {
            resultMap.put("status", 500);
            resultMap.put("message", "用户已经存在");
            //resultMap.put("id", "" + user.getId());
        }
        else
        {
            resultMap.put("status", 200);
            resultMap.put("message", "注册成功");
            userService.adduser(user);
        }
        return resultMap;
    }
}
