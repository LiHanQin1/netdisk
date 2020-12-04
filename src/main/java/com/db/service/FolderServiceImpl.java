package com.db.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.db.bean.Folder;
import com.db.mapper.FolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements IFolderService {
    @Autowired
    FolderMapper folderMapper;

    @Override
    public String getFolderTreeJson() {
        //读取数据库数据
        List<Folder> list = folderMapper.selectList(null);
        Folder root = new Folder(0, "顶层节点", 0);
        //转换json数据
        for (Folder p : list
        ) {
            root.add(new Folder(p.getId(), p.getfName(), p.getPid()));
        }
        //转json串 GSON  fastjson 第三方对象和json转换工具
        String json = JSON.toJSONString(root);
        //将整体的json串转对象
        JSONObject jsonObject = JSON.parseObject(json);
        //获取到对象当中的数组
        JSONArray array = jsonObject.getJSONArray("children");
        return JSON.toJSONString(array);
    }

    @Override
    public Folder getFolderById(Integer id) {
        Folder folder = folderMapper.selectById(id);
        return folder;
    }
}
