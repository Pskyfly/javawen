package com.example.demo.bean;

import lombok.Data;

import java.util.Date;

@Data//注解，设置set和get方法
public class UserT {
    private int id;
    private String name;
    private String password;
    private String email;
    private String birthday;
    private String money;
    public void copyuser(UserT user)
    {
        this.id=user.getId();
        this.name=user.getName();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.birthday=user.getBirthday();
        this.money=user.getMoney();
    }
}
