package com.tao.hava.example.shiro.entity;

import lombok.Data;

/**
 * @ClassName Admin
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:25
 **/
@Data
public class Admin {

    private Long id;
    private String username;
    private String password;
    private Boolean lock;
}
