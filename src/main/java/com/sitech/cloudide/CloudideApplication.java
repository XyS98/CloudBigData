/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 13:15:51
 * @LastEditTime: 2020-08-02 20:03:47
 */ 
package com.sitech.cloudide;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sitech.cloudide.mapper")
public class CloudideApplication {
   public static void main(String[] args) {
        SpringApplication.run(CloudideApplication.class, args);
    }
}