package com.example.demo.controller;

import com.example.demo.bean.Userwrites;
import com.example.demo.service.UserService;
import com.example.demo.tools.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class DocManageController {
    @Resource
    UserService userService;//所有的service都在这里，并没有分开

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    @RequestMapping(value="/writerlist",method= RequestMethod.GET)
    @ResponseBody
    public Object getWriterList(int page, int limit ,String name)
    {
        resultMap.clear();
        resultMap.put("status", 200);
        resultMap.put("code", 0);
        if(Objects.equals(name, "")) {

            List<Userwrites> writers = userService.getWriterList();
            Object some = Tools.writerBuildPage(writers, limit, page);
            resultMap.put("data", some);
            resultMap.put("count", writers.size());
            resultMap.put("msg", "用户列表");
            return resultMap;
        }
        else
        {
            if(userService.getWriterbyname(name)==null)
            {
                resultMap.put("msg", "用户不存在");
                resultMap.put("count",0);
                resultMap.put("data",null);
                return resultMap;
            }
            else
            {
                List<Userwrites> list = new ArrayList<>();
                list.add(userService.getWriterbyname(name));
                resultMap.put("data",list);
                resultMap.put("count", 1);
                resultMap.put("msg","找到该用户");
                return resultMap;
            }
        }
    }

    @RequestMapping(value="/setOperatingWriter",method= RequestMethod.GET)
    @ResponseBody
    public Object setOperating(String name)
    {
        resultMap.clear();
        Userwrites writer=userService.getWriterbyname(name);
        if(writer==null)
        {
            resultMap.put("status",500);
            resultMap.put("message","作者不存在");
        }
        else
        {
            Tools.operatingwriter.copyWriter(writer);
            resultMap.put("status",200);
            resultMap.put("message","找到该作者");
        }
        return resultMap;
    }
    @RequestMapping(value="/getOperatingWriter",method= RequestMethod.GET)
    @ResponseBody
    public Object getOperating()
    {
        resultMap.clear();

        if(Tools.operatingUser==null)
        {
            resultMap.put("status",500);
            resultMap.put("message","未获取当前作者");
        }
        else
        {
            resultMap.put("status",200);
            resultMap.put("message","获取当前作者");
            String name=Tools.operatingwriter.getUsername();
            resultMap.put("data",userService.findUserbynme(name));
        }
        return resultMap;
    }
}
