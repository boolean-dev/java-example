package com.tao.java.example.transactional.dao;

import com.tao.java.example.transactional.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName MainDao
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/4/3 10:14
 **/
@Mapper
public interface MainDao {

    @Insert("INSERT INTO `user` VALUES (NULL, #{name}, #{age}, #{sex})")
    void save(User user);
}
