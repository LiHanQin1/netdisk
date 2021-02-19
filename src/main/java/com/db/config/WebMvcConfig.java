package com.db.config;

import com.db.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Spring boot方式
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
/**
 * 配置添加拦截器
 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //第一个拦截器
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/js/**")
//                .excludePathPatterns("/lib/**")
//                .excludePathPatterns("/css/**")
//                .excludePathPatterns("/fonts/**")
//                .excludePathPatterns("/images/**")
//                .excludePathPatterns("/index.html")
//                .excludePathPatterns("/login.html");
//
//        //第二个拦截器
//        registry.addInterceptor(new WatchTimerInterceptor())
//                .addPathPatterns("/user/**")
//                .excludePathPatterns("/js/**")
//                .excludePathPatterns("/lib/**")
//                .excludePathPatterns("/css/**")
//                .excludePathPatterns("/fonts/**")
//                .excludePathPatterns("/images/**")
//                .excludePathPatterns("/index.html")
//                .excludePathPatterns("/login.html");
//
////        性能检测登录拦截器
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/toLogin")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/login.html")
//                .excludePathPatterns("/common_share.html");;
//                .excludePathPatterns("/folder_tree.html")
//                .excludePathPatterns("/main_list_file.html")
//                .excludePathPatterns("/main.html")
//                .excludePathPatterns("/menu_mainlist.html")
//                .excludePathPatterns("/top.html")
//                .excludePathPatterns("/share.html");

    }
}