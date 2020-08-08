/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-07 14:30:51
 * @LastEditTime: 2020-08-07 15:15:03
 */
package com.sitech.cloudide.service;

import com.sitech.cloudide.bean.MachineStats;
import java.util.List;
public interface MachineStatsService {

    // 将主机性能数据插入数据库
    public int insertinsertMachineStats(List<MachineStats> stats);

    public int insertinsertMachineStat(MachineStats stats);

    
}