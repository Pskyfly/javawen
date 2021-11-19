package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import com.example.demo.tools.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class UserListController {
    @Resource
    UserService userService;
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    @RequestMapping(value="/list",method= RequestMethod.GET)
    @ResponseBody
    public Object getUserList(int page, int limit,String name)
    {
        resultMap.clear();
        resultMap.put("status", 200);
        resultMap.put("code", 0);
//        String name=Tools.lastquery;
        if(Objects.equals(name, "")) {

            List<UserT> users = userService.getUserList();
            Object some = Tools.buildPage(users, limit, page);
            resultMap.put("data", some);
            resultMap.put("count", users.size());
            resultMap.put("msg", "用户列表");
            return resultMap;
        }
        else
        {
            if(userService.findUserbynme(name)==null)
            {

                resultMap.put("msg", "用户不存在");
                resultMap.put("count",0);
                resultMap.put("data",null);
                return resultMap;
            }
            else
            {
                List<UserT> list = new ArrayList<>();
                list.add(userService.findUserbynme(name));
                resultMap.put("data",list);
                resultMap.put("count", 1);
                resultMap.put("msg","找到该用户");
                return resultMap;
            }
        }
    }
    @RequestMapping(value="/list",method= RequestMethod.POST)
    @ResponseBody
    public Object getnewUserList(int page, int limit,String name)
    {
        //System.out.println(name);
        resultMap.clear();
        resultMap.put("status", 200);
        resultMap.put("code", 0);
        if(Objects.equals(name, "")) {

            List<UserT> users = userService.getUserList();
            Object some = Tools.buildPage(users, limit, page);
            resultMap.put("data", some);
            resultMap.put("count", users.size());
            resultMap.put("msg", "用户列表");
            return resultMap;
        }
        else
        {
            if(userService.findUserbynme(name)==null)
            {

                resultMap.put("msg", "用户不存在");
                resultMap.put("count",0);
                resultMap.put("data",null);
                return resultMap;
            }
            else
            {
                List<UserT> list = new ArrayList<>();
                list.add(userService.findUserbynme(name));
                resultMap.put("data",list);
                resultMap.put("count", 1);
                resultMap.put("msg","找到该用户");
                return resultMap;
            }
        }
    }
    @RequestMapping(value="/delete",method= RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUser(String name)
    {
        UserT user=userService.findUserbynme(name);
        resultMap.clear();
        if(user==null)
        {
            resultMap.put("status",1);
            resultMap.put("message","删除失败");
            return resultMap;
        }
        else {
            userService.deleteUser(user.getId());
            resultMap.put("status",0);
            resultMap.put("message","删除成功");
            return resultMap;
        }
    }

    @RequestMapping(value="/update",method= RequestMethod.PUT)
    @ResponseBody
    public Object updateUser(UserT user)
    {
        UserT auser=userService.findUserbynme(user.getName());
        resultMap.clear();
        if(auser==null)
        {
            resultMap.put("status",1);
            resultMap.put("message","修改失败");
            return resultMap;
        }
        else {
            userService.updateUser(user);
            resultMap.put("status",0);
            resultMap.put("message","修改成功");
            return resultMap;
        }
    }
}
