package com.db.test;

import com.db.service.IFolderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FolderTest {

    @Autowired
    IFolderService folderService;
@Test
    public void test01() {
        String str = folderService.getFolderTreeJson();
        System.out.println(str);
    }
}
