package com.tao.java.example.shiro.service;

import com.tao.java.example.shiro.dao.AdminDao;
import com.tao.java.example.shiro.entity.Admin;
import com.tao.java.example.shiro.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public Map<String,Object> getRolesAndPermissionsByUserName(String username) {

        Map<String,Object> result = new HashMap<>(2);
        List<Role> roles = adminDao.getRolesByUserName(username);
        List<String> perssions = adminDao.getRolesAndPermissionsByUserName(username);
        Set<String> set = new HashSet<>(perssions);
        result.put("allRoles",roles.stream().map(Role::getName).collect(Collectors.toList()));
        result.put("allPermissions",perssions);
        return result;
    }

    public Admin getUserByUserName(String username) {
        return adminDao.getUserByUserName(username);
    }
}
