package com.tao.java.example.shiro.dao;

import com.tao.java.example.shiro.entity.Admin;
import com.tao.java.example.shiro.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName AdminDao
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 15:28
 **/
@Mapper
@Repository
public interface AdminDao {

    @Insert("INSERT INTO `admin` (`username`, `password`) VALUES (#{username}, #{password});")
    Integer save(Admin admin);

    @Select("SELECT authority FROM admin t1, admin_role t2, role_authority t3 WHERE t1.username = #{username} AND t1.id = t2.admin_id AND t2.role_id = t3.role_id")
    List<String> getRolesAndPermissionsByUserName(String username);

    @Select("")
    List<Role> getRolesByUserName(String username);

    @Select("SELECT * FROM `admin`  t WHERE t.username = #{username}")
    Admin getUserByUserName(String username);
}
