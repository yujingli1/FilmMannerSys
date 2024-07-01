package com.stesla.love.user.mapper;

import com.stesla.love.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    void save(User user);
    void update(User user);
    User findById(@Param("userId") int userId);
    void deleteById(@Param("userId") int userId);
    List<User> findAll();
}
