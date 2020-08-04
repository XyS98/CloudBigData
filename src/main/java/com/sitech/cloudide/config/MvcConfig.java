/*
 * @Descripttion: 将请求重定向到 /templates
 * @Author: yjb
 * @Date: 2020-07-31 21:02:52
 * @LastEditTime: 2020-07-31 21:26:38
 */
package com.sitech.cloudide.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("/templates");
    System.out.println("-----重定向---templates-----");
    }
}