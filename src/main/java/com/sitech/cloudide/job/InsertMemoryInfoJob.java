/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-02 21:31:38
 * @LastEditTime: 2020-08-03 21:29:28
 */
/*
 * @Descripttion: 定时使用SSH工具，获取主机Memory信息
 * @Author: yjb
 * @Date: 2020-08-02 21:31:38
 * @LastEditTime: 2020-08-03 17:04:55
 */
package com.sitech.cloudide.job;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;
import com.sitech.cloudide.utils.MemoryAnalysis;
import com.sitech.cloudide.utils.RemoteShellExcuteUtil;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class InsertMemoryInfoJob implements Job {
    
    @Autowired
    private RemoteShellExcuteUtil remoteShellExcuteUtil;
    @Autowired
    private MemoryAnalysis memoryAnalysis;
    @Autowired
    private MemoryInfoImpl memoryInfoImpl;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("-----定时使用Shell工具，获取主机Memory信息-----");

        remoteShellExcuteUtil.getLinuxInfo("192.168.6.131", "root", "root");
        try {
            String runInfo = remoteShellExcuteUtil.exec("free -m");
            List<MemoryInfo> memoryInfos = memoryAnalysis.analysisMemory(runInfo);            
            int i = memoryInfoImpl.insertMemoryInfo(memoryInfos);
            if(i==0){
                System.out.println("信息插入失败" + new Date() +"\n");
            }else{
                System.out.println("信息插入成功" + new Date() +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}