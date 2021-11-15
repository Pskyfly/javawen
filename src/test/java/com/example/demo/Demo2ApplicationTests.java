package com.example.demo;

import com.example.demo.bean.UserT;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
//@MapperScan("com.example.demo.mapper")
@SpringBootTest
class Demo2ApplicationTests {
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
        UserT userBean = userService.loginIn("kate","123");
        System.out.println("该用户ID为：");
        System.out.println(userBean.getId());
    }
}
