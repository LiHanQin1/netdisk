package com.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.bean.Files;
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

}
