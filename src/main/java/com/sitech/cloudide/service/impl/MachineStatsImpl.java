/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-07 15:09:23
 * @LastEditTime: 2020-08-07 15:39:34
 */
package com.sitech.cloudide.service.impl;

import java.util.List;

import com.sitech.cloudide.bean.MachineStats;
import com.sitech.cloudide.mapper.MachineStatsMapper;
import com.sitech.cloudide.service.MachineStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineStatsImpl implements MachineStatsService {

    @Autowired
    private MachineStatsMapper mapper;

    @Override
    public int insertinsertMachineStats(List<MachineStats> stats) {
        return mapper.insertMachineStats(stats);
    }

    @Override
    public int insertinsertMachineStat(MachineStats stats) {
        return mapper.insertinsertMachineStat(stats);
    }
    
}