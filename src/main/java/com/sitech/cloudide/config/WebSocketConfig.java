/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-04 13:22:17
 * @LastEditTime: 2020-08-06 16:00:18
 */
package com.sitech.cloudide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

// 使用Springboot 单元测试前需要注释掉 @Configuration
// @Configuration
public class WebSocketConfig {
    /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}