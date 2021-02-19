package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

@TableName(value = "t_dept")
public class DeptTree {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "deptname")
    private  String title; //部门名称

    @TableField(value = "pid")
    private Integer deptID; //部门id

    @TableField(exist = false)
    private List<DeptTree> children=new ArrayList<>();

    @TableField(exist = false)
    private boolean checked;

    public DeptTree() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeptTree{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", deptID=" + deptID +
                ", children=" + children +
                ", checked=" + checked +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public List<DeptTree> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTree> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public DeptTree(Integer id, String title, Integer deptID) {
        this.id = id;
        this.title = title;
        this.deptID = deptID;
    }

    public void add(DeptTree node) {
        if ("0".equals(node.deptID)){
            this.children.add(node);
        }else if(node.deptID.equals(this.id)){
            this.children.add(node);
        }else{
            for (DeptTree tem_node : children){
                tem_node.add(node);
            }
        }
    }
}
