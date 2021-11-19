package com.example.demo.tools;

import com.example.demo.bean.UserT;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String COUNT = "count";
    private static final String DATA = "data";
    public static UserT operatingUser = new UserT();
    public static Object buildPage(List<UserT> datas,int limits,int nowpage) {
        List<UserT> list = new ArrayList<>();
        for(int i=(nowpage-1)*limits;i< datas.size()&&i<(nowpage-1)*limits+limits;i++)
        {
            list.add(datas.get(i));
        }
        return list;
    }

}
