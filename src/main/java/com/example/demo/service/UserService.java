package com.example.demo.service;

import com.example.demo.bean.UserT;

import java.util.List;

public interface  UserService {
    UserT loginIn(String name, String password);
}
