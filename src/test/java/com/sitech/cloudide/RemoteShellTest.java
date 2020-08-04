/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 16:19:23
 * @LastEditTime: 2020-08-03 15:12:07
 */
/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 16:19:23
 * @LastEditTime: 2020-07-30 17:09:20
 */
package com.sitech.cloudide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sitech.cloudide.utils.MemoryAnalysis;
import com.sitech.cloudide.utils.RemoteShellExcuteUtil;

 import org.junit.Test;
 import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

 @RunWith(SpringRunner.class)
 @SpringBootTest(classes = CloudideApplication.class)
 public class RemoteShellTest {
     @Autowired
     private MemoryAnalysis memoryAnalysis;
     @Autowired
     private RemoteShellExcuteUtil remoteShellExcuteUtil;

     @Test
     public void getShellInfo() throws IOException{
        String ip ="192.168.6.131";
        String username = "root";
        String password = "root";
        String command = "free -m";
        remoteShellExcuteUtil.getLinuxInfo(ip, username, password);
        String result = remoteShellExcuteUtil.exec(command);
        System.out.println(result);
     }
    
 }