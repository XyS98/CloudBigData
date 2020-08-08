/*
 * @Descripttion:Linux主机内存信息的数据服务接口
 * @Command free -m
 * @Author: yjb
 * @Date: 2020-08-01 13:39:24
 * @LastEditTime: 2020-08-06 13:56:27
 */
package com.sitech.cloudide.service;

import java.io.IOException;
import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;

public interface MemoryInfoService {

    // 使用ssh工具从Linxu主机获取Memory数据
    public List<MemoryInfo> getMemoryInfos(String ip, String username, String password,String command,int linux_id) throws IOException;
    
    //内存信息添加至数据库
    public int insertMemoryInfo(List<MemoryInfo> memoryInfos);

    //查询主机内存总信息 mem+Swap
    public List<MemoryInfo> selectMemoryInfos();

    //查询主机内存信息 mem/Swap
    public MemoryInfo selectMemoryInfo();
    
    // 获取当前最新的数据
    public List<MemoryInfo> getLastedMemoryInfo();
}