package com.db.controller;

import com.db.bean.Admin;
import com.db.bean.User;
import com.db.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @RequestMapping("/toLogin_admin")
    public ModelAndView toLogin(){
        System.out.println("call toLogin_admin........");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login_admin");
        return mv;
    }

    @RequestMapping("/login_admin")
    public Map<String,String> login(Admin admin , HttpSession session){
        System.out.println("call login_admin ");
        Map<String,String> map = new HashMap<>();
        boolean flag = adminService.login(admin);
        //登录成功
        if (flag){
            session.setAttribute("login",admin);
        }
        map.put("flag",flag+"");
        return map;
    }
}
