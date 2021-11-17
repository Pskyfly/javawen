package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import com.example.demo.tools.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserListController {
    @Resource
    UserService userService;
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    @RequestMapping(value="/list",method= RequestMethod.GET)
    @ResponseBody
    public Object getUserList(int page, int limit)
    {
        resultMap.clear();
        List<UserT> users=userService.getUserList();
        Object some = Tools.buildPage(users,limit,page);
        resultMap.put("data",some);
        resultMap.put("status",200);
        resultMap.put("code",0);
        resultMap.put("msg","用户列表");
        resultMap.put("count",users.size());
        return resultMap;
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

}
