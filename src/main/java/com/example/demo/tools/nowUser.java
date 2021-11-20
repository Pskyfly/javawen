package com.example.demo.tools;

import com.example.demo.bean.UserT;

public class nowUser {
    public static UserT nowuser=new UserT();
    public static int logstatus=0;
    public static void setUser(UserT user)
    {
        nowuser.copyuser(user);
    }
    public static int judgeLogin()
    {
        if(logstatus==0) return 0;
        else return 1;
    }
}
