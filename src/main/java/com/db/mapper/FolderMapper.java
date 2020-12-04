package com.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.bean.Folder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FolderMapper extends BaseMapper<Folder> {
    public List<Folder> getFolderByUname();
}
