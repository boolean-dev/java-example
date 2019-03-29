package com.tao.java.example.shiro.controller;

import com.tao.java.example.shiro.entity.Admin;
import com.tao.java.example.shiro.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        Session session = SecurityUtils.getSubject().getSession();
//        SecurityUtils.getSubject().
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

    @RequestMapping(value = "/admin/add",method = RequestMethod.GET)
    public Object add(Admin admin) {
        adminService.save(admin);
        Map<String,Object> result = new HashMap<>(4);
        result.put("code",201);
        result.put("msg","success");
        return result;
    }

    @RequiresPermissions("admin:del")
    @RequestMapping(value = "/admin/delete",method = RequestMethod.GET)
    public Object delete(Admin admin) {
        adminService.save(admin);
        Map<String,Object> result = new HashMap<>(4);
        result.put("code",202);
        result.put("msg","success");
        return result;
    }
}
