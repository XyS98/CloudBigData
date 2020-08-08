/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-30 20:06:06
 * @LastEditTime: 2020-08-07 18:55:12
 */
package com.sitech.cloudide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sitech.cloudide.bean.LinuxInfo;
import com.sitech.cloudide.bean.MachineStats;
import com.sitech.cloudide.service.impl.LinuxInfoImpl;
import com.sitech.cloudide.service.impl.MachineStatsImpl;
import com.sitech.cloudide.ssh.SSHException;
import com.sitech.cloudide.utils.SSHUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SSHUtilTest {
    @Autowired
    private LinuxInfoImpl linuxInfoImpl;
    @Autowired
    private MachineStatsImpl machineStatsImpl ;


    @Test
    public void testMymethod() {
        List<LinuxInfo> linuxInfos = linuxInfoImpl.getLinxuInfo();
        List<MachineStats> stats = new ArrayList<>();
        Date date = new Date();
        String currentTime = "";
            for(LinuxInfo linux : linuxInfos ){
                int linux_id = linux.getLinux_id();
                String ip = linux.getLinux_ip() ;
                int port = linux.getLinux_port();
                String username =linux.getLinux_userName();
                String password =linux.getLinux_passward();
                String originStr = "";
                try {
                    MachineStats machineStats = SSHUtil.getMachineInfo(ip, port, username, password);
                    // originStr = SSHUtil.execute(ip, port, username, password, cmd);
                    // List<String> strs = StringUtil.findAllByRegex(originStr, " ");
                    machineStats.setLinux_id(linux_id); // SSHUtil返回的linux_id为0，需要加上对应的linux_id;
                    long time = date.getTime();
                    currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
                    machineStats.setSelectedTime(currentTime);
                    System.out.println(machineStats.toString());
                    stats.add(machineStats);
                  
                    
    
                } catch (SSHException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(stats.toString());
            int result = machineStatsImpl.insertinsertMachineStats(stats);   
    }
}   

