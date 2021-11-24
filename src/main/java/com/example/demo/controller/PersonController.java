package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    public Object SelfInfo(HttpSession session){
        resultMap.clear();
        resultMap.put("status","200");
        resultMap.put("message","返回当前登录的用户");
        String username=(String)session.getAttribute("username");
        resultMap.put("data",userService.findUserbynme(username));
        return resultMap;
    }
    @RequestMapping(value = "/selfupdate",method = RequestMethod.POST)
    @ResponseBody
    public Object Selfupdate(UserT user,HttpSession session){
        resultMap.clear();
        UserT auser=userService.findUserbynme(user.getName());
        user.setId(auser.getId());
        user.setPassword(auser.getPassword());
        userService.updateUser(user);
        session.setAttribute("username",user.getName());
        resultMap.put("status", 200);
        resultMap.put("message", "个人信息修改成功");
        return resultMap;
    }

    @RequestMapping(value = "/selfpassword",method = RequestMethod.POST)
    @ResponseBody
    public Object Selfpassword(String password,HttpSession session){
        resultMap.clear();
        String username=(String)session.getAttribute("username");
        UserT user=userService.findUserbynme(username);
        user.setPassword(password);
        userService.updateUser(user);
        resultMap.put("status",200);
        resultMap.put("message","个人信息修改成功");
        return resultMap;
    }

    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    @ResponseBody
    public Object Updatepassword(String lastpassword,String newpassword,HttpSession session){
        resultMap.clear();
        UserT user=userService.findUserbynme((String)session.getAttribute("username"));
        if(Objects.equals(lastpassword, user.getPassword()))
        {
            user.setPassword(newpassword);
            userService.updateUser(user);
            resultMap.put("status",200);
            resultMap.put("message","修改密码成功");
        }
        else
        {
            resultMap.put("status",500);
            resultMap.put("message","原密码错误");
        }
        return resultMap;
    }

}
