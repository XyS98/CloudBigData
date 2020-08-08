/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-02 21:31:38
 * @LastEditTime: 2020-08-07 13:19:57
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

import com.sitech.cloudide.bean.LinuxInfo;
import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.impl.LinuxInfoImpl;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;
import com.sitech.cloudide.ssh.SSHException;
import com.sitech.cloudide.utils.MemoryAnalysis;
import com.sitech.cloudide.utils.RemoteShellExcuteUtil;
import com.sitech.cloudide.utils.SSHUtil;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class InsertMemoryInfoJob implements Job {
    // private static Map<String,>
    @Autowired
    private RemoteShellExcuteUtil remoteShellExcuteUtil;

    @Autowired
    private MemoryAnalysis memoryAnalysis;
    @Autowired
    private MemoryInfoImpl memoryInfoImpl;
    @Autowired
    private LinuxInfoImpl linuxInfoImpl;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // System.out.println("-----定时使用Shell工具，获取主机Memory信息-----");
        // System.out.println("-----------------------------信息获取解析插入任务开始--------------------------" + "\n");
        // List<LinuxInfo> linuxInfos = linuxInfoImpl.getLinxuInfo();
        // System.out.println("linuxInfos:" + linuxInfos.toString());
        // int linxu_id = 0;
        // String ip = null;
        // String username = null;
        // String password = null;

        // for (LinuxInfo info : linuxInfos) {
        //     linxu_id = info.getLinux_id();
        //     ip = info.getLinxu_ip();
        //     username = info.getLinux_userName();
        //     password = info.getLinux_passward();
        //     System.out.println("查询的主机信息info:" + info.toString());

        //     try {
        //         String runInfo = SSHUtil.execute(ip, 22, username, password, "free -m");
        //         List<MemoryInfo> memoryInfos = memoryAnalysis.analysisMemory(runInfo, linxu_id);
        //         System.out.println("~~~^^^^^^^^^^将解析的数据插入到Mysql^^^^^^^^^^^^^~~~" + "\n");
        //         int i = memoryInfoImpl.insertMemoryInfo(memoryInfos);
        //         if (i == 0) {
        //             System.out.println("~~信息插入失败~~" + "\n");
        //         } else {
        //             System.out.println("~~信息插入成功~~" + "\n");
        //         }
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     } catch (SSHException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
        // System.out.println("-----------------------------信息获取解析插入任务结束--------------------------" + "\n");
    }
}