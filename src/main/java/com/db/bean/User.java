package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //用户Id
    @TableField(value = "uname")
    private String uname;
    @TableField(value = "upass")
    private String upass;
    @TableField(value = "deptid")
    private Integer deptID; //部门id

    public User() {
    }

    public User(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }

    public User(Integer id, String uname, String upass) {
        this.id = id;
        this.uname = uname;
        this.upass = upass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public Integer getDeptid() {
        return deptID;
    }

    public void setDeptid(Integer deptid) {
        this.deptID = deptid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", deptid=" + deptID +
                '}';
    }
}
