package com.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.bean.User;
import com.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
//    @Autowired
//    RoleMapper roleMapper;
//    @Autowired
//    PermissionMapper permissionMapper;
    @Override
    public boolean login(User user) {
        QueryWrapper queryWrapper=new QueryWrapper();
        Map<String,String> map=new HashMap<>();
        map.put("uname",user.getUname());
        map.put("upass",user.getUpass());
        queryWrapper.allEq(map);
        List<User>list=userMapper.selectList(queryWrapper);
        if (list!=null&&list.size()>0){
            user.setId(list.get(0).getId());
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
    @Override
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void editUser(User user) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("uname", user.getUname());
        updateWrapper.set("upass", user.getUpass());
        updateWrapper.set("deptid", user.getDeptid());
        updateWrapper.eq("id", user.getId());
        userMapper.update(user, updateWrapper);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }
}
