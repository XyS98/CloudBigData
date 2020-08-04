/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 11:20:19
 * @LastEditTime: 2020-08-03 11:23:29
 */
package com.sitech.cloudide;

import java.lang.reflect.Field;

import com.sitech.cloudide.bean.MemoryInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudideApplication.class)
public class FiledsTest {

    @Autowired
    private MemoryInfo memoryInfo;
    
    @Test
    public void fieldsTest(){
        Field[] memoryInfoFileds = memoryInfo.getClass().getFields();
        for(Field field:memoryInfoFileds){
         System.out.print(field +"/n");
        }
    }
}