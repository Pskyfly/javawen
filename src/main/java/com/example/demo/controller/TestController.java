package com.example.demo.controller;

import com.example.demo.bean.UserT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String home1(Model model) {
        model.addAttribute("param1", 3);
        UserT parameter=new UserT();
        parameter.setId(111111);
        parameter.setName("liyafei");
        model.addAttribute("parame", parameter);
        return "test";
    }
}
