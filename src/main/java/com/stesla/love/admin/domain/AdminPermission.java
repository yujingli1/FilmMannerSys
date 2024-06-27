package com.stesla.love.admin.domain;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

/**
 * CREATE TABLE AdminPermissions (
 *     AdminID INT,
 *     PermissionID INT,
 *     FOREIGN KEY (AdminID) REFERENCES Admins(AdminID),
 *     FOREIGN KEY (PermissionID) REFERENCES Permissions(PermissionID),
 *     PRIMARY KEY (AdminID, PermissionID)
 * );
 */
public class AdminPermission {
    private int adminID;
    private int permissionID;

    public AdminPermission() {
    }

    public AdminPermission(int adminID, int permissionID) {
        this.adminID = adminID;
        this.permissionID = permissionID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
