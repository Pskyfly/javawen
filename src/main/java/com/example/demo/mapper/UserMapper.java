package com.example.demo.mapper;

import com.example.demo.bean.UserT;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper
{
   UserT getInfo(UserT userT);
   List<UserT> getUserList();
   UserT getUserbyname(String name);
   void addUser(UserT user);
   void deleteUser(int id);
}

