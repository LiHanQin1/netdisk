package com.db.service;

import com.db.bean.File;
import com.db.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements IFileService{

    @Autowired
    FileMapper fileMapper;
    @Override
    public void save(File file) {
        fileMapper.insert(file);
    }
}
