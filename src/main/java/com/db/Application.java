package com.db;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;



@SpringBootApplication()
@MapperScan(basePackages = "com.db.mapper")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    /**
     * 限制上传文件大小
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大 5m 可以使用读取配置
        factory.setMaxFileSize(DataSize.parse("512000KB")); //KB,MB
        /// 设置总上传数据总大小 50m
        factory.setMaxRequestSize(DataSize.parse("51200000KB"));
        return factory.createMultipartConfig();
    }


}
