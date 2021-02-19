package com.db.test;

import com.db.bean.Files;
import com.db.mapper.FileMapper;
import com.db.service.IFileService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {
    @Autowired
    IFileService fileService;
    @Autowired
    FileMapper fileMapper;

    @Test
    public void downFile() {
        Files file = fileService.getFileById(8);
        System.out.println(file);
    }


}
