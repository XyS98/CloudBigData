package com.sitech.cloudide.job;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMemoryInfoJob implements Job {
    private static MemoryInfo memoryInfo;

    @Autowired
    private MemoryInfoImpl memoryInfoImpl;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("-----定时从Mysql查询Memory最新信息-----");
             memoryInfo = memoryInfoImpl.getLastedMemoryInfo();
            System.out.println(memoryInfo.toString() + new Data() + "/n");
        System.out.println("-----查询结束-----");

    }

    /**获取装填的数据
     * @return memoryInfo
     */
    public MemoryInfo getMemoryInfo(){
        return memoryInfo;
    }

}