package com.db.controller;

import com.db.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@RestController
public class EncryptionController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/to_download_encryption")
    public ModelAndView toDownloadEncryption() {
        System.out.println("call to_encryption");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("download_encryption");
        return mv;
    }

    @RequestMapping("/download_encryption")
    public Map<String, String> login(HttpSession session,@RequestParam(value = "code") String code) {
        Map<String, String> map = new HashMap<>();
        String str = (String) session.getAttribute("code");
        map.put("str", str);
        return map;
    }

}
