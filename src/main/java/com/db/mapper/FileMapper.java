package com.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.bean.Files;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper  extends BaseMapper<Files> {
    public List<Files> getFilesByUname();

}
