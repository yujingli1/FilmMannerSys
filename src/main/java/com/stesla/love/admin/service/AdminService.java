package com.stesla.love.admin.service;
import com.stesla.love.admin.domain.Admin;



public interface AdminService {
    // 根据用户名查询用户
    Admin findAdminByUsername(String username);
    // 校验密码
    boolean checkPassword(String username, String password);
    // 根据用户名和密码注册用户
    void registerByUsernameAndPassword(String username, String password);
    // 更新用户密码
    void update(Admin admin);

    // 根据用户ID查询用户
    Admin selectAdminByAdminID(int adminId);
}
