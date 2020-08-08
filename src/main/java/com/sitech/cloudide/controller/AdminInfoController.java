/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 15:41:30
 * @LastEditTime: 2020-08-04 13:26:44
 */
package com.sitech.cloudide.controller;

import com.sitech.cloudide.service.impl.AdminInfoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class AdminInfoController {

    @Autowired
    private AdminInfoImpl adminInfoImpl;
    // @Autowired
    // private MemoryInfoImpl memoryInfoImpl;

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
