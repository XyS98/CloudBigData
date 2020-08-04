/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 13:14:01
 * @LastEditTime: 2020-08-03 13:42:18
 */
package com.sitech.cloudide;

import java.util.ArrayList;
import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudideApplication.class)
public class InsertMemoryTest {
    
    @Autowired
    private MemoryInfoImpl memoryInfoImpl;
    @Autowired
    private MemoryInfo memoryInfo;
    
    @Test
    public void insertMemory(){
        List<MemoryInfo> memoryInfoList = new ArrayList<MemoryInfo>();
        memoryInfo.setLinux_id(3001);
        memoryInfo.setMemkid(1); // 添加信息种类 1 mem
        memoryInfo.setTotal(1);
        memoryInfo.setUsed(2);
        memoryInfo.setFree(3);
        memoryInfo.setShared(4);
        memoryInfo.setBuffORcache(5);
        memoryInfo.setAvailable(6);
        memoryInfo.setSelectedTime(null);
        memoryInfoList.add(memoryInfo);
        System.out.println(memoryInfo.toString());
    
        int result = memoryInfoImpl.insertMemoryInfo(memoryInfoList);
        System.out.println(result);
    }
}