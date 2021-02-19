package com.db.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.db.bean.Department;
import com.db.bean.DeptTree;
import com.db.mapper.DeptMapper;
import com.db.mapper.DeptTreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    DeptMapper deptMapper;

    @Autowired
    DeptTreeMapper deptTreeMapper;

    @Override
    public Department getDeptById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public List<Department> getDepartments() {
        return deptMapper.selectList(null);
    }

    @Override
    public void saveDepartment(Department department) {
        deptMapper.insert(department);
    }

    @Override
    public void editDepartment(Department department) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("deptName",department.getDeptName());
        updateWrapper.set("deptID",department.getDeptID());
        updateWrapper.eq("id",department.getId());
        deptMapper.update(department,updateWrapper);
    }

    @Override
    public void deleteDepartment(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public String getDeptTreeJson() {
        //数据库获取数据
        //List<Department> list = deptMapper.selectList(null);

        List<DeptTree> list = deptTreeMapper.selectList(null);
        //转Json
        DeptTree root = new DeptTree(0,"顶层节点",0);
        for (DeptTree d:list
        ) {
            root.add(new DeptTree(d.getId(),d.getTitle(),d.getDeptID()));
        }
        //转Json串   GSON/fastjson 第三方对象和转换工具
        String json = JSON.toJSONString(root);
        System.out.println("json:"+json);
        //将整体的JSON串转对象
        JSONObject jsonObject = JSON.parseObject(json);
        //获取到对象的数组
        JSONArray array = jsonObject.getJSONArray("children");
        System.out.println("array:"+JSON.toJSONString(array));
        return JSON.toJSONString(array);
    }
}
