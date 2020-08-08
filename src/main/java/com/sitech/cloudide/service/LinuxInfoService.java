/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-06 09:44:14
 * @LastEditTime: 2020-08-06 09:53:47
 */
package com.sitech.cloudide.service;

import java.util.List;

import com.sitech.cloudide.bean.LinuxInfo;

public interface LinuxInfoService {

    // 查询主机信息与用户

    public List<LinuxInfo> getLinxuInfo();     
    
}