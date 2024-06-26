package com.stesla.love.admin.controller;


import com.stesla.love.admin.domain.Admin;
import com.stesla.love.utils.PostImgUtil;
import com.stesla.love.utils.ResultUtil;
import com.stesla.love.admin.service.AdminService;
import com.stesla.love.utils.JwtUtil;
import com.stesla.love.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 注册
     *
     * @param map{username,password}
     * @return ResultUtil
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        // 判断用户名和密码是5-16位
        if (username == null || username.length() < 5 || username.length() > 16) {
            return ResultUtil.error(400, "Username must be 5-16 characters long!").getMap();
        }
        // 从数据库中查询用户是否存在
        Admin admin = adminService.findAdminByUsername(username);
        // 如果存在，返回错误信息
        // 如果不存在，将用户信息插入数据库
        if (admin != null) {
            return ResultUtil.error(400, "User already exists!").getMap();
        }
        try {
            adminService.registerByUsernameAndPassword(username, password);
        } catch (Exception e) {
            return ResultUtil.error(500, e).getMap();
        }
        return ResultUtil.success().getMap();

    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        // 从数据库中查询用户是否存在
        Admin admin = adminService.findAdminByUsername(username);
        // 如果不存在，返回错误信息
        if (admin == null) {
            return ResultUtil.error(400, "User does not exist!").getMap();
        }
        // 对密码进行加密(使用MD5加密)
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!s.equals(admin.getPassword())) {
            return ResultUtil.error(400, "Password error!").getMap();
        }
        admin.setPassword(null); // 隐藏密码
        // 生成jwt令牌
        Map<String, Object> map1 = new HashMap<>();
        map1.put("admin", admin);
        String token = JwtUtil.getToken(map1);
        return ResultUtil.success(token).getMap();
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        try {
            Map<String, Object> map1 = ThreadLocalUtil.get();
            return ResultUtil.success(map1).getMap();
        } catch (Exception e) {
            return ResultUtil.error(401, "Token error!").getMap();
        }
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> map) {

            Map<String, Object> map1 = ThreadLocalUtil.get();
            Map<String,Object> adminObj = (Map<String, Object>) map1.get("admin");

            // 遍历admin,存入Admin对象
            Admin admin = new Admin();
            for (Map.Entry<String, Object> entry : adminObj.entrySet()) {
                switch (entry.getKey()) {
                    case "adminID" -> admin.setAdminID((Integer) entry.getValue());
                    case "username" -> admin.setUsername((String) entry.getValue());
                    case "password" -> admin.setPassword((String) entry.getValue());
                    case "email" -> admin.setEmail((String) entry.getValue());
                    case "fullName" -> admin.setFullName((String) entry.getValue());
                    case "registrationDate" -> admin.setRegistrationDate((String) entry.getValue());
                }
            }

            if (!adminService.checkPassword(admin.getUsername(),(String) map.get("password"))){  // 校验密码
                return ResultUtil.error(400, "Password error!").getMap();
            }

            if(map.containsKey("newPassword")){  // 校验新密码
                String newPassword = (String) map.get("newPassword");
                if(newPassword.length() < 5 || newPassword.length() > 16) {  // 校验新密码长度
                    return ResultUtil.error(400, "New password must be 5-16 characters long!").getMap();
                }
                admin.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            }
            if(map.containsKey("email")){  // 校验邮箱
                String email = (String) map.get("email");
                // 邮箱正则表达式
                String emailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                if(!email.matches(emailReg)) {
                    return ResultUtil.error(400, "Email format error!").getMap();
                }
                admin.setEmail(email);
            }
            if(map.containsKey("fullName")){  // 校验全名
                String fullName = (String) map.get("fullName");
                if(fullName.length() < 2 || fullName.length() > 16) {  // 校验全名长度
                    return ResultUtil.error(400, "Full name must be 2-16 characters long!").getMap();
                }
                admin.setFullName(fullName);
            }

            adminService.update(admin);
            return ResultUtil.success().getMap();

//            return ResultUtil.error(401, e.getMessage()).getMap();

    }

    @PostMapping("/updateImg")
    public Map<String, Object> updateImg(@RequestParam("file") MultipartFile file) {

        
        //保存图片到本地
         String path = "C:\\Users\\Stesla\\IdeaProjects\\FilmSysBackend\\";
         String fileName = file.getOriginalFilename();
         File dest = new File(path + fileName);
         try {
             file.transferTo(dest);
         } catch (IOException e) {
             e.printStackTrace();
         }
        Map<String,String> map = PostImgUtil.uploadImg(dest);

        Map<String, Object> map1 = ThreadLocalUtil.get();
        Map<String,Object> adminObj = (Map<String, Object>) map1.get("admin");
        int adminID = (int) adminObj.get("adminID");
        Admin admin = adminService.selectAdminByAdminID(adminID);
        admin.setImgURL(map.get("url"));
        admin.setImgHash(map.get("hash"));
        adminService.update(admin);

        // 删除本地图片
        dest.delete();

        return ResultUtil.success(map).getMap();
    }


    @PostMapping("/test")
    public Map<String, Object> test() {
        return ResultUtil.success("我列个豆测试成功").getMap();
    }
}

