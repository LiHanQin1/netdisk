package com.db.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.db.bean.Files;
import com.db.bean.Folder;
import com.db.bean.User;
import com.db.mapper.FileMapper;
import com.db.mapper.FolderMapper;
import com.db.service.IFileService;
import com.db.service.IFolderService;
import com.db.utils.Result;
import com.db.utils.ResultEnum;
import com.db.utils.ResultUtils;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    IFileService fileService;
    @Autowired
    IFolderService folderService;
    @Autowired
    FolderMapper folderMapper;
    @Autowired
    FileMapper fileMapper;


    @RequestMapping("/to_tree")
    public ModelAndView to_tree(String ids) {
        ModelAndView modelAndView = new ModelAndView();
        //获取参数，保存参数
        modelAndView.addObject("ids", ids);
        modelAndView.setViewName("folder_tree");
        return modelAndView;
    }

    /**
     * 单个文件共享设置为共享状态
     *
     * @param
     * @return
     */
    @RequestMapping("/to_file_share_person")
    public String toFileSharePerson(@RequestParam(value = "ids") String ids, Integer pid, HttpSession session) {
        System.out.println("ids=" + ids + "pid=" + pid);
        String[] split = ids.split("\\,");
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Integer> list = new ArrayList<>();
        long code1 = Math.round((Math.random() + 1) * 1000);
        String code = String.valueOf(code1);
        session.setAttribute("code",code);
        for (String id : split
        ) {
            list.add(Integer.valueOf(id));
        }
        for (Integer fid : list
        ) {
            //通过文件id，查到所有选中的文件具体信息
            Files files = fileService.getFileById(fid);
            files.setStatus(1);
            queryWrapper.eq("id", fid);
            if (fileMapper.update(files, queryWrapper) <= 0) {
                return "error";
            }
        }
        return "http://localhost:8084/netdisk/to_download_encryption" + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp提取码："+code;
    }

    /**
     * 文件复制
     *
     * @param
     * @return
     */
    @RequestMapping("/fileCopy")
    public String fileCopy(@RequestParam(value = "ids") String ids, Integer pid) {
        System.out.println("ids=" + ids + "pid=" + pid);
        String[] split = ids.split("\\,");

        for (String id : split
        ) {
            Files files = fileService.getFileById(Integer.valueOf(id));
            Folder folders = folderService.getFolderById(pid);
            if (files.getPid() == folders.getPid()) {
                return "同目录下";
            } else {
                files.setPid(folders.getPid());
                fileService.save(files);
                return "移动成功";
            }
        }
        return "sucess";
    }

    /**
     * 文件移动
     *
     * @param
     * @return
     */
    @RequestMapping("/fileMove")
    public String fileMove(@RequestParam(value = "ids") String ids, Integer pid) {
        System.out.println("ids=" + ids + "pid=" + pid);
        String[] split = ids.split("\\,");
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Integer> list = new ArrayList<>();
        for (String id : split
        ) {
            list.add(Integer.valueOf(id));
        }

        for (Integer fid : list
        ) {
            //通过文件id，查到所有选中的文件具体信息
            Files files = fileService.getFileById(fid);
            if (files.getPid() == pid) {
                continue;
            }
            files.setPid(pid);
            queryWrapper.eq("id", fid);
            if (fileMapper.update(files, queryWrapper) <= 0) {
                return "error";
            }

        }
        return "移动成功";
    }


    /**
     * 上传文件
     */
    @RequestMapping("/uploadfile")
    //@ResponseBody//json交互注解
    public Result uploadFile(@RequestParam("choosefile") MultipartFile file, Files files, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
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
        String lastStr = file.getOriginalFilename();
        if(lastStr.endsWith(".jpg")){
            files.setTypeid(1);
        }
        if(lastStr.endsWith(".png")){
            files.setTypeid(1);
        }
        if(lastStr.endsWith(".ppt")){
            files.setTypeid(2);
        }
        if(lastStr.endsWith(".video")){
            files.setTypeid(3);
        }
        if(lastStr.endsWith(".jpg")){
            files.setTypeid(1);
        }
        if(lastStr.endsWith(".mp3")){
            files.setTypeid(4);
        }
        if(lastStr.endsWith(".pdf")){
            files.setTypeid(5);
        }
        if(lastStr.endsWith(".mp4")){
            files.setTypeid(4);
        }
        if(lastStr.endsWith(".doc")){
            files.setTypeid(4);
        }
        if(lastStr.endsWith(".zip")){
            files.setTypeid(5);
        }
        files.setCreateMan(user.getId());
        files.setPid(0);
        files.setfName(file.getOriginalFilename());
        files.setfSize((int) file.getSize());
        files.setFurl(upload.getAbsolutePath());
        files.setCreateTime(df.format(new Date()));
        files.setIsDir(0);
        files.setStatus(0);
        fileService.save(files);
        Map<String, Object> map = new HashMap<>();
        map.put("src", file.getOriginalFilename());
        map.put("code", "0");
        map.put("msg", "");
        result.setData(map);
        System.out.println("上传成功");
        return result;
    }

    /**
     * 文件下载 单个下载可自定义 存储路径
     *
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    //@RequestMapping(value = "/downfile/{id}")    @PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response
    @RequestMapping("/downloadfile")
    public void downfile(@RequestParam(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("call downloadfile");
            Files dfile = fileService.getFileById(id);
            System.out.println(dfile);
            //设置头信息进行下载文件
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-msdownload");//需要弹出保存框
            response.setHeader("Content-Disposition" //Content-Disposition是头信息
                    , "attachment;filename*=UTF-8''" + URLEncoder.encode(dfile.getfName(), "UTF-8"));
            //下载文件的路径
            String path = "E:\\IDEA\\netdisk\\target\\classes\\static\\user\\data" + dfile.getfName();
            System.out.println(path + "--");
            FileInputStream in = new FileInputStream(new File("path"));
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            //下载下来的路径
            FileOutputStream out = new FileOutputStream(new File("E:\\IDEA\\netdisk\\target\\classes\\static\\user") + dfile.getfName());
            OutputStreamWriter osw = new OutputStreamWriter(out, "Unicode");
            char[] cbuf = new char[1024];
            int length = 0;
            while ((length = isr.read(cbuf)) != -1) {
                osw.write(cbuf);
                osw.flush();
            }
            isr.close();
            osw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
        }
    }


    @RequestMapping("/to_main_list_file")
    public ModelAndView to_main_list_file(@Param(value = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Files> list2 = new ArrayList<>();
        List<Files> files = fileService.selectLists();
        System.out.println(files);
        System.out.println("id = "+id);
        for (Files file : files
        ) {
            System.out.println("pid = "+file.getPid());
            if (file.getPid().equals(id)) {
                list2.add(file);
            }
        }
        System.out.println("list2" + list2);
        modelAndView.addObject("list2", list2);
        modelAndView.setViewName("main_list_file");
        return modelAndView;
    }

    @RequestMapping("/to_main")
    public ModelAndView to_main(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        User user = (User) session.getAttribute("user");
        System.out.println("11111" + user);
        List<Files> list = fileService.selectFiles(user.getId());
        modelAndView.addObject("id", user.getId());
        modelAndView.addObject("file", list);

        return modelAndView;
    }

    /**
     * 注册公共共享页面
     */
    @RequestMapping("/to_common_share")
    public ModelAndView to_common_share(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        //获取参数，保存参数
        modelAndView.setViewName("common_share");
        System.out.println("call common_shaare");
        //List<Files> list2 = fileService.selectLists();
        //User user = (User) session.getAttribute("user");
        List<Files> list2 = fileService.selectLists();
        //modelAndView.addObject("id", user.getId());
        modelAndView.addObject("list2", list2);
        // modelAndView.addObject("file", list2);
        return modelAndView;
    }

//    /**
//     * 注册公共共享页面
//     */
//    @RequestMapping("/to_common_share")
//    public ModelAndView toCommonShareWithJSONString(HttpSession session) {
//        ModelAndView modelAndView = new ModelAndView();
//        //获取参数，保存参数
//        modelAndView.setViewName("common_share");
//        User user = (User) session.getAttribute("user");
//        List<Files> list2 = fileService.selectLists();
//        Gson gson = new Gson();
//        List<String> stringList = new ArrayList<>();
//        for (int i = 0; i < list2.size(); i++) {
//            String str = gson.toJson(list2.get(i));
//            stringList.add(str);
//        }
//        modelAndView.addObject("id", user.getId());
//        modelAndView.addObject("list2", stringList);
//        // modelAndView.addObject("file", list2);
//        return modelAndView;
//    }

//    /**
//     * 注册公共共享页面
//     */
//    @RequestMapping("/to_common_share_file")
//    public ModelAndView to_common_share_file(@Param(value = "status") Integer status) {
//        ModelAndView modelAndView = new ModelAndView();
//        //获取参数，保存参数
//        List<Files> list2 = fileService.selectLists();
//        modelAndView.addObject("list2", list2);
//        modelAndView.setViewName("common_share_file");
//        return modelAndView;
//    }

    //删除操作
    @RequestMapping("/file_delete")
    public Result deleteFile(Integer id) {
        System.out.println("call deleteFile");
        Result result = null;
        try {
            fileService.deteList(id);
            result = ResultUtils.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = ResultUtils.error(ResultEnum.DELETE_FAILS.getCode(), ResultEnum.DELETE_FAILS.getMsg());
        }
        return result;
    }

    @RequestMapping("/to_Files_tree")
    public ModelAndView toPermissionTree() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Files_tree");
        return mv;
    }

    @RequestMapping("/Files_tree")
    public String Permission_tree() {
        String json = fileService.getFilesTreeJson();
        System.out.println(json);
        return json;
    }
}
