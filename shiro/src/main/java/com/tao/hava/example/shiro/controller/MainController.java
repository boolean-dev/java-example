package com.tao.hava.example.shiro.controller;

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
}
