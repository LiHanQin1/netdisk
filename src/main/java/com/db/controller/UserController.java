package com.db.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.bean.User;
import com.db.service.IUserService;
import com.db.utils.Result;
import com.db.utils.ResultEnum;
import com.db.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController=@Controller+  @ResponseBody
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @RequestMapping("/toLogin")
    public ModelAndView toLogin() {
        System.out.println("call tologin");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/login")
    public Map<String,String> login(User user,HttpSession session) {
        Map<String, String> map = new HashMap<>();
        boolean flag=userService.login(user);
        if (flag){
            session.setAttribute("user",user);
        }
        map.put("flag",flag+"");
        return map;
    }

    @RequestMapping("/to_user_list")
    public ModelAndView getUserList(){
        System.out.println("call to user_list");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("user_list");
        return mv;
    }

    @RequestMapping("/user_list")
    public Result selectUserList(){
        List<User> list = userService.getUsers();
        Result result = ResultUtils.success(list);
        result.setCode(0);//layui 动态表格接口默认code=0
        return result;
    }

    @RequestMapping("/to_user_add")
    public ModelAndView to_user_add(){
        System.out.println("call to user_add");
        ModelAndView mv= new ModelAndView();
        mv.setViewName("user_add");
        return mv;
    }

    @RequestMapping("/add_user")
    public Result addUser(User user) {
        System.out.println("add_user 确认增加");
        System.out.println("user:" + user);
        userService.saveUser(user);
        return ResultUtils.success();
    }

    @RequestMapping("/to_edit_user")
    public ModelAndView editUser(@RequestParam(value = "id") Integer id) {
        System.out.println("edit");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_edit");
        User user = userService.getUserById(id);
        System.out.println(user);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/user_delete")
    public Result delUser(Integer id) {
        System.out.println("user_delete id=" + id);
        try {
            userService.deleteUser(id);
            return ResultUtils.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultUtils.error(ResultEnum.DELETE_FAILS.getCode(), ResultEnum.DELETE_FAILS.getMsg());
        }
    }

    @RequestMapping("/user_edit")
    public Result editUser(User user) {
        System.out.println("call user_edit");
        System.out.println("user:" + user);
        userService.editUser(user);
        return ResultUtils.success();
    }
}
