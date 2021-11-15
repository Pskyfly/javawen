package com.example.demo.servicelmpl;

import com.example.demo.bean.UserT;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private  UserMapper userMapper;

    @Override
    public UserT loginIn(String name, String password) {
        UserT user=new UserT();
        user.setName(name);
        user.setPassword(password);
        return userMapper.getInfo(user);
        //return new UserT();
    }
}
