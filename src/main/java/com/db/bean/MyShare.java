package com.db.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_myshare")
public class MyShare {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "fid")
    private  String fID; //文件id

    @TableField(value = "plink")
    private  String pLink; //文件链接

    @TableField(value = "ptype")
    private  String pType; //文件类型

    @TableField(value = "createtime")
    private  String createTime; //文件类型

    @TableField(value = "downloadnum")
    private  String downloadNum; //下载次数

    public MyShare() {
    }

    public MyShare(String fID, String pLink, String pType, String createTime) {
        this.fID = fID;
        this.pLink = pLink;
        this.pType = pType;
        this.createTime = createTime;
    }

    public MyShare( String fID, String pLink, String pType, String createTime, String downloadNum) {
        this.fID = fID;
        this.pLink = pLink;
        this.pType = pType;
        this.createTime = createTime;
        this.downloadNum = downloadNum;
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

    public String getpLink() {
        return pLink;
    }

    public void setpLink(String pLink) {
        this.pLink = pLink;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    @Override
    public String toString() {
        return "MyShare{" +
                "id=" + id +
                ", fID='" + fID + '\'' +
                ", pLink='" + pLink + '\'' +
                ", pType='" + pType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", downloadNum='" + downloadNum + '\'' +
                '}';
    }
}
