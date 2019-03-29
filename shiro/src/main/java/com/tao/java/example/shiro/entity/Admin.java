package com.tao.java.example.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Admin
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:25
 **/
@Data
public class Admin implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Boolean lock;
}
