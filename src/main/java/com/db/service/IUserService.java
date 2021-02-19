package com.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.bean.User;

import java.util.List;

public interface IUserService {
//    public boolean login(User user);

//    public User getUserById(Integer id);
//
//    public List<User> getUsers();

//    public void save(User user);
//
//    public void update(User user);
//
//    public IPage<User> selectUserPage(Page<User> page, String uname, String startTime, String endTime);
//
//    public void delete(Integer id);
//
//    public User getUserByUname(String uname);

    public User getUserById(Integer id);
    public boolean login(User user);
    public List<User> getUsers();
    public void saveUser(User user);
    public void editUser(User user);
    public void deleteUser(Integer id);}


