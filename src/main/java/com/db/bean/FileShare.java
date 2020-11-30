package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_file_share")
public class FileShare {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "fid")
    private  String fID; //分享的文件id

    @TableField(value = "uid")
    private  String uID; //分享给用户Id,谁可以看到

    @TableField(value = "depid")
    private  String depID; //部门id

    public FileShare() {
    }

    public FileShare(String fID, String uID, String depID) {
        this.fID = fID;
        this.uID = uID;
        this.depID = depID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfID() {
        return fID;
    }

    public void setfID(String fID) {
        this.fID = fID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }

    @Override
    public String toString() {
        return "FileShare{" +
                "id=" + id +
                ", fID='" + fID + '\'' +
                ", uID='" + uID + '\'' +
                ", depID='" + depID + '\'' +
                '}';
    }
}
