package com.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.bean.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptMapper extends BaseMapper<Department> {
}
