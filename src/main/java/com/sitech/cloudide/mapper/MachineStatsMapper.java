/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-07 15:10:18
 * @LastEditTime: 2020-08-07 15:39:17
 */
package com.sitech.cloudide.mapper;

import java.util.List;
import com.sitech.cloudide.bean.MachineStats;

public interface MachineStatsMapper {
    

    public int insertMachineStats(List<MachineStats> stats);

    public int insertinsertMachineStat(MachineStats stats);

}