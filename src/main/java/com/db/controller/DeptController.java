package com.db.controller;

import com.db.bean.Department;
import com.db.bean.User;
import com.db.mapper.DeptMapper;
import com.db.service.IDeptService;
import com.db.utils.Result;
import com.db.utils.ResultEnum;
import com.db.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DeptController {

    @Autowired
    IDeptService deptService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/to_dept_list")
    public ModelAndView getUserList(){
        System.out.println("call to dept_list");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("dept_list");
        return mv;
    }

    @RequestMapping("/dept_list")
    public Result selectDeptList(){
        List<Department> list = deptService.getDepartments();
        Result result = ResultUtils.success(list);
        result.setCode(0);//layui 动态表格接口默认code=0
        return result;
    }

    @RequestMapping("/to_dept_add")
    public ModelAndView to_dept_add(){
        System.out.println("call to dept_add");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("dept_add");
        return mv;
    }

    @RequestMapping("/add_dept")
    public Result addUser(Department department) {
        System.out.println("add_dept 确认增加");
        System.out.println("dept:" + department);
        deptService.saveDepartment(department);
        return ResultUtils.success();
    }

    @RequestMapping("/dept_delete")
    public Result delDept(Integer id) {
        System.out.println("dept_delete id=" + id);
        try {
            deptService.deleteDepartment(id);
            return ResultUtils.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultUtils.error(ResultEnum.DELETE_FAILS.getCode(), ResultEnum.DELETE_FAILS.getMsg());
        }
    }

    @RequestMapping("/to_dept_edit")
    public ModelAndView editdept(@RequestParam(value = "id") Integer id) {
        System.out.println("edit dept");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dept_edit");
        Department department = deptService.getDeptById(id);
        System.out.println(department);
        modelAndView.addObject("department", department);
        return modelAndView;
    }

    @RequestMapping("/dept_edit")
    public Result editUser(Department department) {
        System.out.println("call dept_edit");
        System.out.println("department:" + department);
        deptService.editDepartment(department);
        return ResultUtils.success();
    }

    @RequestMapping("dept_tree")
    public String permission_tree(HttpSession session){
        System.out.println("进入 dept_tree ");
        String json = deptService.getDeptTreeJson();
        System.out.println("json="+json);
        return json;
    }

    @RequestMapping("/to_dept_tree")
    public ModelAndView getDept_tree(){
        System.out.println("call to dept_tree");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("dept_tree");
        return mv;
    }
}
