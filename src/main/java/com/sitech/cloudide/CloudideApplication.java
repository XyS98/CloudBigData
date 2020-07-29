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