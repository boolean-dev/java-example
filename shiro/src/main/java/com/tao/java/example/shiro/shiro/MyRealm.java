package com.tao.java.example.shiro.shiro;

import com.tao.java.example.shiro.entity.Admin;
import com.tao.java.example.shiro.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName MyRealm
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/3/28 16:00
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        Map<String, Object> map = null;
        try {
            map = this.adminService.getRolesAndPermissionsByUserName(userName);
            auth.setRoles((Set<String>) map.get("allRoles"));
            auth.setStringPermissions((Set<String>) map.get("allPermissions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auth;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        Admin admin = this.adminService.getUserByUserName(userName);
        if (admin == null) {
            throw new UnknownAccountException("该用户名称不存在！");
        } else if (admin.getLock()) {
            throw new UnknownAccountException("该用户已经被锁定了！");
        } else {
            String password = new String((char[]) token.getCredentials());
            if (admin.getPassword().equals(password)) {
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), admin.getUsername());
                SecurityUtils.getSubject().getSession().setAttribute("currentUser", admin);
                return authcInfo;
            } else {
                throw new IncorrectCredentialsException("密码错误！");
            }
        }
    }
}
