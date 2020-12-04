package com.db.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView index(){
        System.out.println("call index ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/to_menu_mainlist")
    public ModelAndView to_menu_mainlist(){
        System.out.println("call to_menu_mainlist ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_mainlist");
        return modelAndView;
    }

    @RequestMapping("/to_top")
    public ModelAndView to_top(){
        System.out.println("call to_top ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("top");
        return modelAndView;
    }


}
