package com.db.service;

import com.db.bean.Folder;

public interface IFolderService {
    public String getFolderTreeJson();

    public Folder getFolderById(Integer id);

}
