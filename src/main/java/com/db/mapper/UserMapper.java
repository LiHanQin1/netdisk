package com.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {


//    public List<User> getUserByUname(String uname);
//
//    public IPage<User> selectUserByPage(Page<User> page, @Param(value = "uname") String uname, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);


}
