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
import java.util.Objects;

@Controller
public class ForgetController {
    @Resource
    UserService userService;

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    @ResponseBody
    public Object Login(String name,String password,String last,ModelMap modelMap){
        resultMap.clear();
        UserT auser = userService.findUserbynme(name);
        if(auser==null)
        {
            resultMap.put("returncode",1);
            resultMap.put("message","用户不存在");
        }
        else if(!Objects.equals(auser.getPassword(), last))
        {
            resultMap.put("returncode",2);
            resultMap.put("message","原密码错误");
        }
        else
        {
            auser.setPassword(password);
            userService.updateUser(auser);
            resultMap.put("returncode",0);
            resultMap.put("message","修改成功");
        }
        return resultMap;
    }
}
