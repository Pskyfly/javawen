package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import com.example.demo.tools.nowUser;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class PersonController {
    @Resource
    UserService userService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @RequestMapping(value = "/loginuser",method = RequestMethod.GET)
    @ResponseBody
    public Object SelfInfo(){
        resultMap.clear();
        UserT user =userService.findUserbynme(nowUser.nowuser.getName());
        resultMap.put("status","200");
        resultMap.put("message","返回当前登录的用户");
        resultMap.put("data",user);
        return resultMap;
    }
    @RequestMapping(value = "/selfupdate",method = RequestMethod.POST)
    @ResponseBody
    public Object Selfupdate(UserT user){
        resultMap.clear();
        if(userService.findUserbynme(user.getName())!=null&& !Objects.equals(user.getName(), nowUser.nowuser.getName()))
        {
            resultMap.put("status",500);
            resultMap.put("message","用户名重复");
            return resultMap;
        }
        else {
            user.setId(nowUser.nowuser.getId());
            user.setPassword(nowUser.nowuser.getPassword());
            userService.updateUser(user);
            resultMap.put("status", 200);
            resultMap.put("message", "个人信息修改成功");
            nowUser.setUser(user);
            return resultMap;
        }
    }

    @RequestMapping(value = "/selfpassword",method = RequestMethod.POST)
    @ResponseBody
    public Object Selfpassword(String password){
        resultMap.clear();
        UserT user=userService.findUserbynme(nowUser.nowuser.getName());
        user.setPassword(password);
        userService.updateUser(user);
        resultMap.put("status",200);
        resultMap.put("message","个人信息修改成功");
        nowUser.setUser(user);
        return resultMap;
    }
}
