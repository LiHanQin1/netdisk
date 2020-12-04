package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_file")
public class Files {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "fName")
    private  String fName; //文件名

    @TableField(value = "fSize")
    private Integer fSize;// 文件大小

    @TableField(value = "isDir")
    private Integer isDir; //是否是目录

    @TableField(value = "furl")
    private  String furl;  //文件路径

    @TableField(value = "pid")
    private Integer pid;  //

    @TableField(value = "createMan")
    private  Integer createMan; //文件创建人

    @TableField(value = "createtime")
    private String createTime;  //创建时间

    @TableField(value = "typeid")
    private Integer typeid;  //

    @TableField(value = "depaid")
    private Integer depaid;  //

    @TableField(value = "filetype")
    private String filetype;  //
    public Files() {
    }

    public Files(String fName, String furl, Integer pid, Integer createMan, String createTime) {
        this.fName = fName;
        this.furl = furl;
        this.pid = pid;
        this.createMan = createMan;
        this.createTime = createTime;
    }

    public Files(Integer id, String fName, Integer fSize, Integer isDir, String furl, Integer pid, Integer createMan, String createTime, Integer typeid, Integer depaid, Integer depaid1, String filetype) {
        this.id = id;
        this.fName = fName;
        this.fSize = fSize;
        this.isDir = isDir;
        this.furl = furl;
        this.pid = pid;
        this.createMan = createMan;
        this.createTime = createTime;
        this.typeid = typeid;
        this.depaid = depaid;
        this.depaid = depaid1;
        this.filetype = filetype;
    }



    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getDepaid() {
        return depaid;
    }

    public void setDepaid(Integer depaid) {
        this.depaid = depaid;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Integer getfSize() {
        return fSize;
    }

    public void setfSize(Integer fSize) {
        this.fSize = fSize;
    }

    public Integer getIsDir() {
        return isDir;
    }

    public void setIsDir(Integer isDir) {
        this.isDir = isDir;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCreateMan() {
        return createMan;
    }

    public void setCreateMan(Integer createMan) {
        this.createMan = createMan;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", fSize=" + fSize +
                ", isDir=" + isDir +
                ", furl='" + furl + '\'' +
                ", pid=" + pid +
                ", createMan=" + createMan +
                ", createTime='" + createTime + '\'' +
                ", typeid=" + typeid +
                ", depaid=" + depaid +
                ", depaid=" + depaid +
                ", filetype='" + filetype + '\'' +
                '}';
    }
}
