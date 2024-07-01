package com.stesla.love.user.service;

import com.stesla.love.user.domain.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);
    void registerByUsernameAndPassword(String username, String password);
    boolean checkPassword(String username, String password);
    void update(User user);
    User findUserById(int userId);
    void deleteUserById(int userId);
    List<User> findAllUsers();
}
