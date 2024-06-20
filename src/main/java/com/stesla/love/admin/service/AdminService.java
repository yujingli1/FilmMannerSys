package com.stesla.love.admin.service;
import com.stesla.love.admin.domain.Admin;



public interface AdminService {
    // 根据用户名查询用户
    Admin findAdminByUsername(String username);
    // 根据用户名和密码注册用户
    void registerByUsernameAndPassword(String username, String password);

}
