/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-04 13:39:12
 * @LastEditTime: 2020-08-04 13:39:40
 */
package com.sitech.cloudide.controller;

import com.sitech.cloudide.service.WebSocketServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        ModelAndView mav=new ModelAndView("/webSocket");
//        mav.addObject("userId", userId);
        return mav;
    }
}