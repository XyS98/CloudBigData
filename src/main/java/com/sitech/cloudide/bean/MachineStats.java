/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-30 18:41:03
 * @LastEditTime: 2020-08-07 16:56:04
 */ 
package com.sitech.cloudide.bean;


import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MachineStats{
    private int stats_id;
    
    private int linux_id;

    private String ip;

    private String cpuUsage;

    private String loads;

    private String traffic;

    private String memoryUsageRatio;

    private String memoryFree;

    private int memoryAllocated;

    private String memoryTotal;

    private String selectedTime;
    
    private String diskUsageMap;

    

    
    // private Map<String/**挂载点*/, String/**使用百分比*/> diskUsageMap;
    
    
    
    public MachineStats() {
    }

    public MachineStats(int stats_id, int hostId, String ip, String cpuUsage, String loads, String traffic,
            String memoryUsageRatio, String memoryFree, int memoryAllocated, String memoryTotal, String diskUsageMap) {
        this.stats_id = stats_id;
        this.ip = ip;
        this.cpuUsage = cpuUsage;
        this.loads = loads;
        this.traffic = traffic;
        this.memoryUsageRatio = memoryUsageRatio;
        this.memoryFree = memoryFree;
        this.memoryAllocated = memoryAllocated;
        this.memoryTotal = memoryTotal;
        this.diskUsageMap = diskUsageMap;
    }

    

}
