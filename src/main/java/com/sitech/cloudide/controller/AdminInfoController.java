package com.sitech.cloudide.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.sitech.cloudide.bean.AdminInfo;
import com.sitech.cloudide.service.impl.AdminInfoImpl;

@RestController
@RequestMapping("/data")
public class AdminInfoController {
    
    @Autowired
    private AdminInfoImpl adminInfoImpl;

    // @RequestMapping("findAll")
    //  public List<AdminInfo> findAll(){
    //      return adminInfoImpl.findAll();
    //  }

    // @RequestMapping("getPhone")
    // public String getPhone(String phone){
    //     return adminInfoImpl.getPhone(phone);
    // }
    @RequestMapping("findByid/{aid}")
    public String findByid(@PathVariable int aid){
        return adminInfoImpl.findById(aid).toString();
    }
}