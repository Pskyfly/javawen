package com.example.demo.service;

import com.example.demo.bean.Doc;
import com.example.demo.bean.UserT;
import com.example.demo.bean.Userwrites;

import java.util.List;

public interface  UserService {
    UserT loginin(String name, String password);
    void adduser(UserT user);
    UserT findUserbynme(String name);
    List<UserT> getUserList();
    void deleteUser(int id);
    void updateUser(UserT user);
//  -------------------------------------------
    List<Userwrites> getWriterList();
    Userwrites getWriterbyname(String username);
    List<Doc> getDocListbyname(String username);
    void deleteDocbyid(int id);
    Doc findDocbyid(int id);
    void updateWriter(Userwrites writer);
    void addDoc(Doc doc);
    void updateDoc(Doc doc);
}
