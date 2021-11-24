package com.example.demo.tools;

import com.example.demo.bean.Doc;
import com.example.demo.bean.UserT;
import com.example.demo.bean.Userwrites;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String COUNT = "count";
    private static final String DATA = "data";
    public static Object userBuildPage(List<UserT> datas, int limits, int nowpage) {
        List<UserT> list = new ArrayList<>();
        for(int i=(nowpage-1)*limits;i< datas.size()&&i<(nowpage-1)*limits+limits;i++)
        {
            list.add(datas.get(i));
        }
        return list;
    }
    public static Object writerBuildPage(List<Userwrites> datas,int limits,int nowpage) {
        List<Userwrites> list = new ArrayList<>();
        for(int i=(nowpage-1)*limits;i< datas.size()&&i<(nowpage-1)*limits+limits;i++)
        {
            list.add(datas.get(i));
        }
        return list;
    }
    public static Object docBuildPage(List<Doc> datas, int limits, int nowpage) {
        List<Doc> list = new ArrayList<>();
        for(int i=(nowpage-1)*limits;i< datas.size()&&i<(nowpage-1)*limits+limits;i++)
        {
            list.add(datas.get(i));
        }
        return list;
    }

}
