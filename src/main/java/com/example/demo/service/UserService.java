package com.example.demo.service;

import com.example.demo.bean.UserT;

import java.util.List;

public interface  UserService {
    UserT loginin(String name, String password);
    void adduser(UserT user);
    UserT findUserbynme(String name);
    List<UserT> getUserList();
}
