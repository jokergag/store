package com.cy.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 * @author joker
 * @create 2022-04-24
 */
@Configuration
public class LoginInterceptor implements WebMvcConfigurer {

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

        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);

    }
}
