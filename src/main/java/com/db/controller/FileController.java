package com.db.controller;

import com.alibaba.fastjson.JSONObject;
import com.db.service.IFileService;
import com.db.utils.Result;
import com.db.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    IFileService fileService;

    /**
     * 上传文件
     */
    @RequestMapping("/uploadfile")
    //@ResponseBody//json交互注解
    public Result uploadFile(@RequestParam("choosefile") MultipartFile file, com.db.bean.File files) throws IOException {
        Result result = ResultUtils.success();
        //上传到服务器的路径
        File projectPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("projectPath=" + projectPath);
        System.out.println("filename=" + file.getOriginalFilename());
        System.out.println("fsize" + file.getSize());
        //上传的位置
        File upload = new File(projectPath, "static/user/data");
        System.out.println("rul" + upload);
        if (!upload.exists()) {
            upload.mkdirs();  //如果没有路径，就创建
        }
        // File.separator 文件路径分隔符（windows/Linux通用，避免使用//）
        File dest = new File(upload.getAbsolutePath() + File.separator + file.getOriginalFilename());
        //文件IO复制
        file.transferTo(dest);
        //数据入库
        result.setCode(0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        //数据封装，存入数据库
        files.setfName(file.getOriginalFilename());
        files.setfSize((int) file.getSize());
        files.setUrl(upload.getAbsolutePath());
        files.setCreateTime(df.format(new Date()));
        files.setIsDir(0);
        fileService.save(files);
        Map<String, Object> map = new HashMap<>();
        map.put("src", file.getOriginalFilename());
        map.put("code", "0");
        map.put("msg", "");
        result.setData(map);
        System.out.println("上传成功");
        return result;
    }


}
