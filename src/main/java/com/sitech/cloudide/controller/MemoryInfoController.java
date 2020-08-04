/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 21:44:51
 * @LastEditTime: 2020-08-03 22:03:02
 */
package com.sitech.cloudide.controller;

import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.job.GetMemoryInfoJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemoryInfoController {
    @Autowired
    private GetMemoryInfoJob getMemoryInfoJob; 

    @RequestMapping("/memory")
    public MemoryInfo getLastedMemoryInfo(){
        return getMemoryInfoJob.getMemoryInfo();
    }
    

}