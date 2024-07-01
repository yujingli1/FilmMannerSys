package com.stesla.love.user.service.impl;

import com.stesla.love.user.domain.User;
import com.stesla.love.user.mapper.UserMapper;
import com.stesla.love.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void registerByUsernameAndPassword(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes())); // MD5加密
        user.setRegisterDate(new Date());
        user.setMembershipLevel(1); // 默认会员等级
        userMapper.save(user);
    }

    @Override
    public boolean checkPassword(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return false;
        }
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        return encryptedPassword.equals(user.getPassword());
    }

    @Override
    public void update(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes())); // MD5加密
        userMapper.update(user);
    }

    @Override
    public User findUserById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public void deleteUserById(int userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAll();
    }
}
