package com.stesla.love.admin.domain;

import com.alibaba.fastjson2.JSONObject;

/**
 * CREATE TABLE Permissions (
 *     PermissionID INT PRIMARY KEY AUTO_INCREMENT,
 *     PermissionName VARCHAR(50) NOT NULL UNIQUE,
 *     Description TEXT
 * );
 */
public class Permission {
    private int permissionID;
    private String permissionName;
    private String description;

    public Permission() {
    }

    public Permission(int permissionID, String permissionName, String description) {
        this.permissionID = permissionID;
        this.permissionName = permissionName;
        this.description = description;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
