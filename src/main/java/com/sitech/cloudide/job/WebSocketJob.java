/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-04 19:22:32
 * @LastEditTime: 2020-08-06 13:57:58
 */
package com.sitech.cloudide.job;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.service.WebSocketServer;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketJob implements Job {
    
    @Autowired
    private MemoryInfoImpl memoryInfoImpl;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketServer();
        if(map.size()!=0){
            for(Map.Entry<String, WebSocketServer> entry:map.entrySet()){
                WebSocketServer webSocket = entry.getValue();
                List<MemoryInfo> memoryInfos = memoryInfoImpl.getLastedMemoryInfo();
                String message = JSON.toJSONString(memoryInfos);
                webSocket.getSession().getAsyncRemote().sendText(message);
                
                System.out.println("每隔两秒钟向前端发送一次数据");
            }
        }else{
            System.out.println("***********WebSocket is closed!************");
        }
        
    }
    
}