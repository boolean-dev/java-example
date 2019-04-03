package com.tao.java.example.transactional.controller;

import com.tao.java.example.transactional.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MainController
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/4/3 10:11
 **/
@Controller
public class MainController {

    @Autowired
    private MainService mainService;


    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public Map<String,Object> index() {
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg","success");
        mainService.save();
        return result;
    }
}
