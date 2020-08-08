/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 13:15:51
 * @LastEditTime: 2020-08-07 13:44:18
 */
package com.sitech.cloudide;

import java.io.IOException;
import java.util.List;

import com.sitech.cloudide.bean.LinuxInfo;
import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.job.InsertMemoryInfoJob;
import com.sitech.cloudide.service.impl.LinuxInfoImpl;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;
import com.sitech.cloudide.utils.MemoryAnalysis;
import com.sitech.cloudide.utils.RemoteShellExcuteUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudideApplication.class)
@MapperScan("com.sitech.cloudide.mapper")


public class DataBaseTest {
    @Autowired(required = true)
    private MemoryInfoImpl memoryInfoImpl;
    @Autowired(required = true)
    private LinuxInfoImpl linuxInfoImpl;
    @Autowired(required = true)
    private RemoteShellExcuteUtil remoteShellExcuteUtil;
    @Autowired(required = true)
    private MemoryAnalysis memoryAnalysis;

    @Test
    public void insertMemoryInfo() throws IOException {
        System.out.println("-----------------------------信息获取解析插入任务开始--------------------------" + "\n");
        List<LinuxInfo> linuxInfos = linuxInfoImpl.getLinxuInfo();
        for(LinuxInfo info:linuxInfos){
            int linxu_id = info.getLinux_id();
            String ip = info.getLinux_ip();
            String user = info.getLinux_userName();
            String passward = info.getLinux_passward();
            remoteShellExcuteUtil.getLinuxInfo(ip,user,passward);
            try {
                String runInfo = remoteShellExcuteUtil.exec("free -m");
                List<MemoryInfo> memoryInfos = memoryAnalysis.analysisMemory(runInfo,linxu_id); 
                System.out.println("~~~^^^^^^^^^^将解析的数据插入到Mysql^^^^^^^^^^^^^~~~"+ "\n");           
                int i = memoryInfoImpl.insertMemoryInfo(memoryInfos);
                if(i==0){
                    System.out.println("~~信息插入失败~~" + "\n");
                }else{
                    System.out.println("~~信息插入成功~~" + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----------------------------信息获取解析插入任务结束--------------------------" + "\n");
    }
    
    /**
     * @MethodName: 
     * @Descripttion: 从数据库LastedMemoryInfo获取最新内存信息
     * @param {void} 
     * @return {type} 
     * @Author: yjb
     * @version: 
     * @Date: 2020-08-04 11:20:10
     */
    @Test
    public void getLastedMemoryInfo(){
       List<MemoryInfo> memoryInfos = memoryInfoImpl.getLastedMemoryInfo();
       for(MemoryInfo mem:memoryInfos)
        System.out.println(mem.toString());
    }
    

    @Test
    public void getLinuxInfo(){
        List<LinuxInfo> linuxInfos = linuxInfoImpl.getLinxuInfo();
        for(LinuxInfo linux:linuxInfos){
           System.out.println("Linuxs:" + linux.toString()); 
        }
    }
}