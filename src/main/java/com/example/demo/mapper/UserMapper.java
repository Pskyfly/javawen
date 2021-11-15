package com.example.demo.mapper;

import com.example.demo.bean.UserT;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper
{
   UserT getInfo(UserT userT);
   UserT getUserList();
}

