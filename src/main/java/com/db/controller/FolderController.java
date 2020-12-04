package com.db.controller;

import com.db.service.IFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class FolderController {
    @Autowired
    IFolderService folderService;

    @RequestMapping("/to_Folder_tree")
    public ModelAndView toPermissionTree(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("Folder_tree");
        return mv;
    }

    @RequestMapping("/Folder_tree")
    public String Permission_tree(){
        String json=folderService.getFolderTreeJson();
        System.out.println(json);
        return json;
    }
}
