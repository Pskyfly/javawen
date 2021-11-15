package com.example.demo.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserT {
    private int id;
    private String name;
    private String password;
    private String email;
    private Date birthday;
    private float money;

}
