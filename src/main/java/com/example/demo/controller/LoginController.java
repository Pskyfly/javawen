package com.example.demo.controller;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    UserService userService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object Login(String name, String password,HttpSession session){
        UserT user = userService.loginin(name, password);
        resultMap.clear();
        if(user!=null) {
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            resultMap.put("id", "" + user.getId());
            session.setAttribute("username", name);
            session.setAttribute("status",1);
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "登录失败");
            session.setAttribute("status",0);
        }
        return resultMap;
    }
    @RequestMapping(value="/logout",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(SessionStatus sessionStatus,HttpSession session){
        try {
            resultMap.clear();
            //session.removeAttribute("user");
            session.setAttribute("status",0);
            resultMap.put("status", 200);
            resultMap.put("message","退出成功");
        }
        catch (Exception e) {
            resultMap.put("status", 500);
        }
        return resultMap;
    }

    @RequestMapping(value = "/getlogstatus",method = RequestMethod.GET)
    @ResponseBody
    public Object checklog(HttpSession session){
        resultMap.clear();
        Object logstatus=session.getAttribute("status");
        if(logstatus== null||(int)logstatus==0)
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
