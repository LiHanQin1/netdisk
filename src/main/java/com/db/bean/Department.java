package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_dept")
public class Department {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "deptname")
    private  String deptName; //部门名称

    @TableField(value = "pid")
    private Integer pID; //部门id

    public Department(String deptName, Integer deptID) {
        this.deptName = deptName;
        this.pID = deptID;
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptID() {
        return pID;
    }

    public void setDeptID(Integer deptID) {
        this.pID = deptID;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptID=" + pID +
                '}';
    }
}
