/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-06 09:54:11
 * @LastEditTime: 2020-08-06 09:55:03
 */
package com.sitech.cloudide.mapper;

import java.util.List;
import com.sitech.cloudide.bean.LinuxInfo;

public interface LinuxInfoMapper {
    
    // 查询主机信息与用户
    public List<LinuxInfo> getLinxuInfo(); 
}