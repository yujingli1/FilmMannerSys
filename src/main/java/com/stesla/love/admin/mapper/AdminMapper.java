package com.stesla.love.admin.mapper;

import com.stesla.love.admin.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;


@Mapper
public interface AdminMapper {
    Admin findAdminByUsername(String username);
    void registerByUsernameAndPassword(String username, String password, Date date);
    List<Admin> selectAdminList(Admin admin);
    Admin selectAdminByAdminID(String adminId);
}
