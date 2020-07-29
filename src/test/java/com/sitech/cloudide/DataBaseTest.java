package com.sitech.cloudide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import com.sitech.cloudide.bean.AdminInfo;
import com.sitech.cloudide.service.impl.AdminInfoImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataBaseTest.class)
@MapperScan("com.sitech.cloudide.mapper")

public class DataBaseTest {
    @Autowired(required = true)
    private AdminInfoImpl impl;

    @Test
    public void findAll(){
        List<AdminInfo> adminInfos = impl.findAll();
        for(int i=0; i<adminInfos.size(); i++){
            System.out.println("aname："+ adminInfos.get(i).getAname()+"\n");
            System.out.println("pwd："+ adminInfos.get(i).getAname()+"\n");
            System.out.println("phone："+ adminInfos.get(i).getAname()+"\n");
        }
    }
}