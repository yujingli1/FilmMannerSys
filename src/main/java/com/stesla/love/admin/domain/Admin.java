package com.stesla.love.admin.domain;

import com.alibaba.fastjson2.JSONObject;

/**
 * CREATE TABLE sys_admins (
 *     AdminID INT PRIMARY KEY AUTO_INCREMENT,
 *     Username VARCHAR(50) NOT NULL UNIQUE,
 *     Password VARCHAR(255) NOT NULL,
 *     Email VARCHAR(100) NOT NULL UNIQUE,
 *     FullName VARCHAR(100),
 *     RegistrationDate DATE NOT NULL
 * );
 */
public class Admin {
    private int adminID;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String registrationDate;
    private String updateDate;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Admin() {
    }

    public Admin(int adminID, String username, String password, String email, String fullName, String registrationDate, String updateDate) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
        this.updateDate = updateDate;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
