package com.tao.java.example.shiro.controller;

import com.tao.java.example.shiro.entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MainController
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:13
 **/
@RestController
public class MainController {

    @RequestMapping(value = "/index")
    public Object index() {
        Map<String,Object> result = new HashMap<>(4);
        result.put("code",200);
        result.put("msg","success");
        return result;
    }

    @RequestMapping(value = "/login")
    public Object login(Admin admin) {
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        //验证码
//        String sToken = (String) params.get("sToken");
//        String textStr = (String) params.get("textStr");
//        boolean flag = userService.checkCodeToken(userInfo.getsToken(), userInfo.getTextStr());
//        if(!flag) {
//            result.put("code", CodeAndMsgEnum.ERROR.getcode());
//            result.put("msg", "验证码错误、或已失效！");
//            return result;
//        }
        try {
            subject.login(token);
            result.put("session",subject.getSession().getId());
        } catch (Exception e) {
            result.put("code", 401);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
