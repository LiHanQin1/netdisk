package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_file")
public class File {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "fName")
    private  String fName; //文件名

    @TableField(value = "fSize")
    private Integer fSize;// 文件大小

    @TableField(value = "isDir")
    private Integer isDir; //是否是目录 1是目录 0是文件

    @TableField(value = "url")
    private  String url;  //文件路径

    @TableField(value = "pid")
    private Integer pid;  //层级模式，挂在哪里

    @TableField(value = "createMan")
    private  Integer createMan; //文件创建人

    @TableField(value = "createtime")
    private String createTime;  //创建时间

    public File() {
    }

    public File(String fName, String url, Integer pid, Integer createMan, String createTime) {
        this.fName = fName;
        this.url = url;
        this.pid = pid;
        this.createMan = createMan;
        this.createTime = createTime;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", createMan=" + createMan +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
