package com.db.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

@TableName(value = "t_folder")
public class Folder {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "fname")
    private String fName;
    //  父节点id
    @TableField(value = "pid")
    private Integer pid;
    @TableField(exist = false)
    private List<Folder> children = new ArrayList<>();

    //递归添加节点
    //递归：方法自己调用自己   且要有一个出口
    public void add(Folder node){
        if ("0".equals(node.pid)){
            this.children.add(node);
        }else if (node.pid.equals(this.id)){
            this.children.add(node);
        }else {
            for (Folder tmp_node :
                    children) {
                tmp_node.add(node);
            }
        }
    }

    public Folder(Integer id, String fName, Integer pid) {
        this.id = id;
        this.fName = fName;
        this.pid = pid;
    }

    public Folder() {
    }

    public Folder(Integer id, String fName, Integer pid, List<Folder> children) {
        this.id = id;
        this.fName = fName;
        this.pid = pid;
        this.children = children;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Folder> getChildren() {
        return children;
    }

    public void setChildren(List<Folder> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", pid=" + pid +
                ", children=" + children +
                '}';
    }

    public static void main(String[] args){
        Folder root = new Folder(0,"顶层节点",0);
        root.add(new Folder(1,"第一层1",0));
        root.add(new Folder(2,"第一层2",0));
        root.add(new Folder(3,"第二层1_2",1));
        root.add(new Folder(4,"第二层2_2",2));
        //转json串
        String json = JSON.toJSONString(root);
        System.out.println(json);
        //将整体的json串转对象
        JSONObject jsonObject = JSON.parseObject(json);
        //获取到对象当中的数组
        JSONArray array = jsonObject.getJSONArray("children");
        System.out.println(JSON.toJSONString(array));
    }


}
