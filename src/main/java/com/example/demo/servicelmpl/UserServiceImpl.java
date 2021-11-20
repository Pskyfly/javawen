package com.example.demo.servicelmpl;

import com.example.demo.bean.Doc;
import com.example.demo.bean.UserT;
import com.example.demo.bean.Userwrites;
import com.example.demo.mapper.DocMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private  UserMapper userMapper;

    @Resource
    private DocMapper docMapper;

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

    @Override
    public void updateUser(UserT user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<Userwrites> getWriterList() {
        return docMapper.getWriterList();
    }

    @Override
    public Userwrites getWriterbyname(String username) {
        return docMapper.getWriterbyname(username);
    }

    @Override
    public List<Doc> getDocListbyname(String username) {
        return docMapper.getDoclistbywriter(username);
    }

    @Override
    public void deleteDocbyid(int id) {
        docMapper.deleteDocbyid(id);
    }

    @Override
    public Doc findDocbyid(int id) {
        return docMapper.findDocbyid(id);
    }

    @Override
    public void updateWriter(Userwrites writer) {
        docMapper.updateWriter(writer);
    }

    @Override
    public void addDoc(Doc doc) {
        docMapper.addDoc(doc);
    }

    @Override
    public void updateDoc(Doc doc) {
        docMapper.updateDoc(doc);
    }
}
