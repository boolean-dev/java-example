package com.tao.java.example.transactional.service;

import com.tao.java.example.transactional.dao.MainDao;
import com.tao.java.example.transactional.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName MainService
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/4/3 10:14
 **/
@Service
public class MainService {

    @Autowired
    private MainDao mainDao;

    @Transactional(rollbackFor = Exception.class)
    public void save() {
        User user1 = new User();
        user1.setName("yjt1");
        user1.setSex("男");
        user1.setAge(24);
        mainDao.save(user1);

        if (true) {
            throw new RuntimeException("测试事务");
        }
        User user2 = new User();
        user2.setName("yjt2");
        user2.setSex("男");
        user2.setAge(24);
        mainDao.save(user1);
    }
}
