package com.example.demo.tools;

import com.example.demo.bean.UserT;

public class nowUser {
    public static UserT nowuser=new UserT();
    public static void setUser(UserT user)
    {
        nowuser.copyuser(user);
    }
}
