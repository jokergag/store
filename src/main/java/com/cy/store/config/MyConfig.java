package com.cy.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 * @author joker
 * @create 2022-04-24
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        HandlerInterceptor interceptor = new com.cy.store.interceptor.LoginInterceptor();

        //创建一个白名单
        List<String> list = new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/web/register.html");
        list.add("/web/login.html");
        list.add("/web/index.html");
        list.add("/web/product.html");
        list.add("/users/reg");
        list.add("/users/login");
        list.add("/districts/**");
        list.add("/products/**");
        list.add("/favicon.ico");
        list.add("/index.html");
        list.add("/");

        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/avatar/**").addResourceLocations("file:D:/BaiduNetdiskDownload/SpringBoot-store/tools/photo/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //第一个ws指定请求路径，第二个/ws指对应的视图名称，这里是/templates/ws.html
        //访问地址http://localhost:8080/ws
        registry.addViewController("/").setViewName("/index.html");
    }
}
