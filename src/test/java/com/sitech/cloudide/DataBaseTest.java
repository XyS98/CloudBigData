/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 13:15:51
 * @LastEditTime: 2020-08-03 21:19:38
 */
package com.sitech.cloudide;

import java.io.IOException;
import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;

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
    private MemoryInfoImpl impl;

    @Test
    public void insertMemoryInfo() throws IOException {
        String command = "free -m";
        List<MemoryInfo> memoryInfos = impl.getMemoryInfos("192.168.6.131", "root", "root",command);
        int result = impl.insertMemoryInfo(memoryInfos);
        if(result == 0 ){
            System.out.println("插入失败");
        }else if(result == 1){
            System.out.println("插入成功");
        }
    }
}