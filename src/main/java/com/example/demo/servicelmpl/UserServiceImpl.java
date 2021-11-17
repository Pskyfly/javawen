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
    public UserT loginin(String name, String password) {
        UserT user=new UserT();
        user.setName(name);
        user.setPassword(password);
        return userMapper.getInfo(user);
        //return new UserT();
    }

    @Override
    public void adduser(UserT user) {
        userMapper.addUser(user);
    }

    @Override
    public UserT findUserbynme(String name) {
        return userMapper.getUserbyname(name);
    }

    @Override
    public List<UserT> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}
