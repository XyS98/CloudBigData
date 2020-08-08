/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-06 09:55:43
 * @LastEditTime: 2020-08-06 10:24:13
 */

package com.sitech.cloudide.service.impl;

import java.util.List;

import com.sitech.cloudide.bean.LinuxInfo;
import com.sitech.cloudide.service.LinuxInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.cloudide.mapper.LinuxInfoMapper;

@Service
public class LinuxInfoImpl implements LinuxInfoService {

    @Autowired
    private LinuxInfoMapper mapper;

    @Override
    public List<LinuxInfo> getLinxuInfo() {
        return mapper.getLinxuInfo();
    }
    
}