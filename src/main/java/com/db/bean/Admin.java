package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_admin")
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //用户Id
    @TableField(value = "admin")
    private String admin;
    @TableField(value = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {
    }

    public Admin(Integer id, String admin, String password) {
        this.id = id;
        this.admin = admin;
        this.password = password;
    }
}
