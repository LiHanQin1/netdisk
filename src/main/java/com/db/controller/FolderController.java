package com.db.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.db.bean.Files;
import com.db.bean.User;
import com.db.mapper.FileMapper;
import com.db.service.IFileService;
import com.db.service.IFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class FolderController {
    @Autowired
    IFolderService folderService;
    @Autowired
    FileMapper fileMapper;
    @Autowired
    IFileService fileService;

//    @RequestMapping("/to_Folder_tree")
//    public ModelAndView toPermissionTree(){
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("Folder_tree");
//        return mv;
//    }
//
//    @RequestMapping("/Folder_tree")
//    public String Permission_tree(){
//        String json=folderService.getFolderTreeJson();
//        System.out.println(json);
//        return json;
//    }

    @RequestMapping("/create_folder")
    public String create_folder(HttpSession session, String folderName, Integer pid) {
        User user = (User) session.getAttribute("user");
        Files file = new Files();
        file.setPid(0);
        file.setIsDir(1);
        file.setCreateMan(user.getId());
        file.setfName(folderName);
        System.out.println(file);
        System.out.println("login user:" + user);
        if (fileMapper.insert(file) > 0) {
            return "创建成功！";
        }
        return "创建失败!";
    }

    @RequestMapping("/file_rename")
    public String file_rename(HttpSession session, String newname, Integer id) {
        System.out.println("file_rename");
        System.out.println("newname:" + newname + "id:" + id);
        User user = (User) session.getAttribute("user");
        Files file = fileService.getFileById(id);
        file.setfName(newname);
        System.out.println(file);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        if (fileMapper.update(file, queryWrapper) > 0) {
            return "修改成功！";
        }
        return "修改失败!";
    }
}
