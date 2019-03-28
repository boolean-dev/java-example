package com.tao.hava.example.shiro.service;

import com.tao.hava.example.shiro.dao.AdminDao;
import com.tao.hava.example.shiro.entity.Admin;
import com.tao.hava.example.shiro.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminService
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:27
 **/
@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public Admin save(Admin admin) {
        Integer i = adminDao.save(admin);
        log.info("--------------i={}",i);
        return admin;
    }

    public List<String> getRolesAndPermissionsByUserName(String username) {

        List<Role> roles = adminDao.getRolesByUserName(username);
        return adminDao.getRolesAndPermissionsByUserName(username);
    }
}
