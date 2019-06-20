package com.tao.java.example.springsecuritysimple.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/6/20 15:18
 **/
@Slf4j
@Controller
public class HelloController {


    @RequestMapping("/")
    public String index() {
        log.info("----------->index");
        return "/index";
    }

    @RequestMapping("/hello")
    public String hello() {
        log.info("----------->hello");
        return "/hello";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        log.info("----------->toLogin");
        return "/login";
    }
    @RequestMapping("/login")
    public String login(String name, String password) {
        log.info("----------->login");
        log.info("name={},password={}", name, password);
        return "/login";
    }

}
