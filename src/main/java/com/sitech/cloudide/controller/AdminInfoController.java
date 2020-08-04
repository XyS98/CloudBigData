/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 15:41:30
 * @LastEditTime: 2020-08-02 20:43:24
 */
package com.sitech.cloudide.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.impl.AdminInfoImpl;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;

@Controller
@RequestMapping("/data")
public class AdminInfoController {

    @Autowired
    private AdminInfoImpl adminInfoImpl;
    @Autowired
    private MemoryInfoImpl memoryInfoImpl;

    // @RequestMapping("findAll")
    // public List<AdminInfo> findAll(){
    // return adminInfoImpl.findAll();
    // }

    // @RequestMapping("getPhone")
    // public String getPhone(String phone){
    // return adminInfoImpl.getPhone(phone);
    // }
    @RequestMapping("findByid/{aid}")
    public String findByid(@PathVariable int aid) {
        return adminInfoImpl.findById(aid).toString();
    }

    // @RequestMapping("/getMemoryInfo")
    // public List<MemoryInfo> getMemInfo() throws IOException {
    //     List<MemoryInfo> memInfos =memoryInfoImpl.getMemoryInfos("192.168.6.131", "root", "root","free -m");
    //     return memInfos;
    // }
}
