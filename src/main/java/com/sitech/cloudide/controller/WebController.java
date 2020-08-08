/*
 * @Descripttion: index.html
 * @Author: yjb
 * @Date: 2020-08-07 20:39:22
 * @LastEditTime: 2020-08-08 12:47:53
 */
package com.sitech.cloudide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}