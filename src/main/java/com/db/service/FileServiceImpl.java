package com.db.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.bean.Files;
import com.db.bean.Folder;
import com.db.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements IFileService{

    @Autowired
    FileMapper fileMapper;
    @Override
    public void save(Files file) {
        fileMapper.insert(file);
    }

    @Override
    public List<Files> selectLists() {
        return fileMapper.selectList(null);
    }

    @Override
    public List<Files> selectFiles(Integer id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("createman",id);
        List<Files> list=fileMapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public Files getFileById(Integer id) {
        Files file=fileMapper.selectById(id);
        return file;
    }

    @Override
    public void deteList(Integer id) {
        fileMapper.deleteById(id);
    }

    @Override
    public String getFilesTreeJson() {
        //读取数据库数据
        List<Files> list = fileMapper.selectList(null);
        Files root = new Files(0, "顶层节点", 0);
        //转换json数据
        for (Files p : list
        ) {
            root.add(new Files(p.getId(), p.getfName(), p.getPid()));
        }
        //转json串 GSON  fastjson 第三方对象和json转换工具
        String json = JSON.toJSONString(root);
        //将整体的json串转对象
        JSONObject jsonObject = JSON.parseObject(json);
        //获取到对象当中的数组
        JSONArray array = jsonObject.getJSONArray("children");
        return JSON.toJSONString(array);
    }

}
