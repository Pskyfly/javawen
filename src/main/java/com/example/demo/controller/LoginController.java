package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import com.example.demo.tools.nowUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.demo.tools.nowUser.logstatus;
import static com.example.demo.tools.nowUser.nowuser;

@Controller
public class LoginController {

    @Resource
    UserService userService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object Login(String name, String password, ModelMap modelMap){
        UserT user = userService.loginin(name, password);
        resultMap.clear();
        if(user!=null) {
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            resultMap.put("id", "" + user.getId());
            logstatus=1;
            nowuser.copyuser(user);
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "登录失败");
            logstatus=0;
        }
        return resultMap;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public Object Logout(){
        logstatus=0;
        resultMap.put("message","退出成功");
        resultMap.put("status",200);
        return resultMap;
    }

    @RequestMapping(value = "/getlogstatus",method = RequestMethod.GET)
    @ResponseBody
    public Object checklog(){
        resultMap.clear();
        if(logstatus==0)
        {
            resultMap.put("logstatus",0);
            resultMap.put("message","未登录");
        }
        else
        {
            resultMap.put("logstatus",1);
            resultMap.put("message","已登录");
        }
        return resultMap;
    }
}
