package com.stesla.love.admin.controller;


import com.auth0.jwt.JWT;
import com.stesla.love.admin.domain.Admin;
import com.stesla.love.admin.domain.Result;
import com.stesla.love.admin.service.AdminService;
import com.stesla.love.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 注册
     * @param map{username,password}
     * @return Result
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String,Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        // 判断用户名和密码是5-16位
        if (username == null || username.length() < 5 || username.length() > 16) {
            return Result.error(400,"Username must be 5-16 characters long!").getMap();
        }
        // 从数据库中查询用户是否存在
        Admin admin = adminService.findAdminByUsername(username);
        // 如果存在，返回错误信息
        // 如果不存在，将用户信息插入数据库
        if (admin != null) {
            return Result.error(400,"User already exists!").getMap();
        }
        try {
            adminService.registerByUsernameAndPassword(username, password);
        }catch (Exception e) {
            return Result.error(500,e).getMap();
        }
        return Result.success().getMap();

    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String,Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        // 从数据库中查询用户是否存在
        Admin admin = adminService.findAdminByUsername(username);
        // 如果存在，返回错误信息
        // 如果不存在，将用户信息插入数据库
        if (admin == null) {
            return Result.error(400,"User does not exist!").getMap();
        }
        // 对密码进行加密(使用MD5加密)
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!s.equals(admin.getPassword())) {
            return Result.error(400,"Password error!").getMap();
        }
        // 生成jwt令牌
        map.put("adminId",admin.getAdminID());
        String token = JwtUtil.getToken(map);
        return Result.success(token).getMap();
    }
}

