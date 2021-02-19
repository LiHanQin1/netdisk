package com.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.bean.Admin;
import com.db.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImlpl implements IAdminService{

    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean login(Admin admin) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, String> map = new HashMap<>();
        map.put("admin", admin.getAdmin());
        map.put("password", admin.getPassword());
        queryWrapper.allEq(map);
        List<Admin> list = adminMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
