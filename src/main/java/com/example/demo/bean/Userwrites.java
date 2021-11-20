package com.example.demo.bean;

import lombok.Data;

@Data
public class Userwrites {
    private int id;
    private String username;
    private int count;

    public void copyWriter(Userwrites writer)
    {
        this.id=writer.getId();
        this.username=writer.getUsername();
        this.count=writer.getCount();
    }

}
