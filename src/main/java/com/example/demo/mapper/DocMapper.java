package com.example.demo.mapper;

import com.example.demo.bean.UserT;
import com.example.demo.bean.Userwrites;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocMapper {
    List<Userwrites> getWriterList();
    Userwrites getWriterbyname(String username);
    void updateWriter(Userwrites writer);
}
