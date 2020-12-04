package com.db.service;

import com.db.bean.Files;

import java.util.List;

public interface IFileService {


    //上传文件
    public void save(Files file);

    public List<Files> selectLists();

    public List<Files> selectFiles(Integer id);

    public Files getFileById(Integer id);
}
