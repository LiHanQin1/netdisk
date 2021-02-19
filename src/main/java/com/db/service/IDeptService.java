package com.db.service;


import com.db.bean.Department;


import java.util.List;

public interface IDeptService {
    public Department getDeptById(Integer id);
    public List<Department> getDepartments();
    public void saveDepartment(Department department);
    public void editDepartment(Department department);
    public void deleteDepartment(Integer id);
    public String getDeptTreeJson();
}
