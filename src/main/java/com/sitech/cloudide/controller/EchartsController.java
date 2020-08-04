/*
 * @Descripttion: 需要数据可视化的界面
 * @Author: yjb
 * @Date: 2020-07-29 15:41:30
 * @LastEditTime: 2020-08-02 19:43:37
 */
package com.sitech.cloudide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EchartsController {
    
    @RequestMapping("/demo")
    public String demo() {
        return "demo.html";
    }
    // 饼状图
    @RequestMapping("/cost")
    public String cost(){
        return "costOfMonth";
    }

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }
}