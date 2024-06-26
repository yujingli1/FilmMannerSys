package com.stesla.love.admin.service.impl;

import com.stesla.love.admin.domain.Admin;
import com.stesla.love.admin.mapper.AdminMapper;
import com.stesla.love.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByUsername(String username) {
        return adminMapper.findAdminByUsername(username);
    }

    @Override
    public void registerByUsernameAndPassword(String username, String password) {
        // 对密码进行加密(使用MD5加密)
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        // 获取当前时间
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        adminMapper.registerByUsernameAndPassword(username, s, sqldate);
    }

    @Override
    public void update(Admin admin) {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        adminMapper.updateInfo(admin,sqldate);
    }

    @Override
    public boolean checkPassword(String username, String password) {
        // 对密码进行加密(使用MD5加密)
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        Admin admin = adminMapper.findAdminByUsername(username);
        return s.equals(admin.getPassword());
    }

    @Override
    public Admin selectAdminByAdminID(int adminId) {
        return adminMapper.selectAdminByAdminID(adminId);
    }

}
