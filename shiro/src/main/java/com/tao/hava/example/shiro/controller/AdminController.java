package com.tao.hava.example.shiro.controller;

import com.tao.hava.example.shiro.entity.Admin;
import com.tao.hava.example.shiro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:26
 **/
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/test")
    public Object index() {
        Map<String,Object> result = new HashMap<>(4);
        result.put("code",200);
        result.put("msg","success");
        return result;
    }

    @RequestMapping(value = "/admin/",method = RequestMethod.POST)
    public Object save(Admin admin) {
        adminService.save(admin);
        Map<String,Object> result = new HashMap<>(4);
        result.put("code",200);
        result.put("msg","success");
        return result;
    }
}
