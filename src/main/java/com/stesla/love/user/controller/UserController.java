package com.stesla.love.user.controller;

import com.stesla.love.user.domain.User;
import com.stesla.love.utils.ResultUtil;
import com.stesla.love.user.service.UserService;
import com.stesla.love.utils.JwtUtil;
import com.stesla.love.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param map {username, password}
     * @return Result
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
        User user = userService.findUserByUsername(username);
        // 如果存在，返回错误信息
        // 如果不存在，将用户信息插入数据库
        if (user != null) {
            return ResultUtil.error(400, "User already exists!").getMap();
        }
        try {
            userService.registerByUsernameAndPassword(username, password);
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
        User user = userService.findUserByUsername(username);
        // 如果不存在，返回错误信息
        if (user == null) {
            return ResultUtil.error(400, "User does not exist!").getMap();
        }
        // 对密码进行加密(使用MD5加密)
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!encryptedPassword.equals(user.getPassword())) {
            return ResultUtil.error(400, "Password error!").getMap();
        }
        user.setPassword(null); // 隐藏密码
        // 生成jwt令牌
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("user", user);
        String token = JwtUtil.getToken(tokenData);
        return ResultUtil.success(token).getMap();
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        try {
            Map<String, Object> tokenData = ThreadLocalUtil.get();
            return ResultUtil.success(tokenData).getMap();
        } catch (Exception e) {
            return ResultUtil.error(401, "Token error!").getMap();
        }
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> map) {
        Map<String, Object> tokenData = ThreadLocalUtil.get();
        Map<String, Object> userObj = (Map<String, Object>) tokenData.get("user");

        // 遍历user,存入User对象
        User user = new User();
        for (Map.Entry<String, Object> entry : userObj.entrySet()) {
            switch (entry.getKey()) {
                case "userId" -> user.setUserId((Integer) entry.getValue());
                case "userName" -> user.setUserName((String) entry.getValue());
                case "password" -> user.setPassword((String) entry.getValue());
                case "email" -> user.setEmail((String) entry.getValue());
                case "registerDate" -> user.setRegisterDate((Date) entry.getValue());
                case "membershipLevel" -> user.setMembershipLevel((Integer) entry.getValue());
            }
        }

        if (!userService.checkPassword(user.getUserName(), (String) map.get("password"))) {  // 校验密码
            return ResultUtil.error(400, "Password error!").getMap();
        }

        if (map.containsKey("newPassword")) {  // 校验新密码
            String newPassword = (String) map.get("newPassword");
            if (newPassword.length() < 5 || newPassword.length() > 16) {  // 校验新密码长度
                return ResultUtil.error(400, "New password must be 5-16 characters long!").getMap();
            }
            user.setPassword(newPassword);
        }
        if (map.containsKey("email")) {  // 校验邮箱
            String email = (String) map.get("email");
            // 邮箱正则表达式
            String emailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            if (!email.matches(emailReg)) {
                return ResultUtil.error(400, "Email format error!").getMap();
            }
            user.setEmail(email);
        }
        if (map.containsKey("userName")) {  // 校验用户名
            String userName = (String) map.get("userName");
            if (userName.length() < 2 || userName.length() > 16) {  // 校验用户名长度
                return ResultUtil.error(400, "Username must be 2-16 characters long!").getMap();
            }
            user.setUserName(userName);
        }

        userService.update(user);
        return ResultUtil.success().getMap();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUserById(@PathVariable("id") int userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResultUtil.error(400, "User not found!").getMap();
        }
        return ResultUtil.success(user).getMap();
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUserById(@PathVariable("id") int userId) {
        try {
            userService.deleteUserById(userId);
            return ResultUtil.success().getMap();
        } catch (Exception e) {
            return ResultUtil.error(500, "Error deleting user!").getMap();
        }
    }

    @GetMapping("/all")
    public Map<String, Object> getAllUsers() {
        return ResultUtil.success(userService.findAllUsers()).getMap();
    }

    @PostMapping("/test")
    public Map<String, Object> test() {
        return ResultUtil.success("Test successful").getMap();
    }
}
