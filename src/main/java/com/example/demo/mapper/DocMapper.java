package com.example.demo.mapper;

import com.example.demo.bean.Doc;
import com.example.demo.bean.UserT;
import com.example.demo.bean.Userwrites;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocMapper {
    List<Userwrites> getWriterList();
    List<Doc> getsimilardocList(String content);
    Userwrites getWriterbyname(String username);
    void updateWriter(Userwrites writer);
    List<Doc> getDoclistbywriter(String username);
    void deleteDocbyid(int id);
    Doc findDocbyid(int id);
    void addDoc(Doc doc);
    void updateDoc(Doc doc);
}
