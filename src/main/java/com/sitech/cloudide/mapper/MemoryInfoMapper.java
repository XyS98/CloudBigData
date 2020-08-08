/*
 * @Descripttion: Linux主机内存信息的数据操作接口
 * @Author: yjb
 * @Date: 2020-08-01 13:50:27
 * @LastEditTime: 2020-08-06 13:56:47
 */
package com.sitech.cloudide.mapper;

import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;

import org.springframework.stereotype.Repository;

@Repository   // 需要在Application中配置 MapperScan扫描该类所在包的路径
public interface MemoryInfoMapper {
      
    //内存信息添加至数据库
    public int insertMemoryInfo(List<MemoryInfo> memoryInfos);

    //查询主机内存总信息 mem+Swap
    public List<MemoryInfo> selectMemoryInfos();

    //查询主机内存信息 mem/Swap
    public MemoryInfo selectMemoryInfo();

    // 获取当前最新的数据
    public List<MemoryInfo> getLastedMemoryInfo();

}