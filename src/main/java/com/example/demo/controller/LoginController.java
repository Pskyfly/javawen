package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    UserService userService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String show(){
        return "login";
    }

    @RequestMapping(value = "/log",method = RequestMethod.POST)
    public Object login(String name,String password){
        //return userService.loginin(name,password).getName();
        return "content.html";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object other(String name, String password, ModelMap modelMap){
        UserT user = userService.loginin(name, password);
        resultMap.clear();
        if(user!=null) {
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            resultMap.put("id", "" + user.getId());
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "登录失败");
        }
        return resultMap;
    }
}
