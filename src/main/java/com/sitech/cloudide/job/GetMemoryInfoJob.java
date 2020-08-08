/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-08-03 21:29:26
 * @LastEditTime: 2020-08-08 15:21:40
 */
package com.sitech.cloudide.job;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.quartz.Job;
import com.alibaba.fastjson.JSON;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sitech.cloudide.bean.MemoryInfo;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ObjIntConsumer;

import org.springframework.stereotype.Component;
import com.sitech.cloudide.service.WebSocketServer;
import com.sitech.cloudide.service.impl.LinuxInfoImpl;
import com.sitech.cloudide.service.impl.MemoryInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class GetMemoryInfoJob implements Job {
    @Autowired
    private  MemoryInfo memoryInfo;
    @Autowired
    private MemoryInfoImpl memoryInfoImpl;
    @Autowired
    private LinuxInfoImpl linuxInfoImpl;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketServer();
        if(map.size()!=0){
            for(Map.Entry<String, WebSocketServer> entry:map.entrySet()){
                WebSocketServer webSocket = entry.getValue();
                System.out.println("-----定时从Mysql查询Memory最新信息-----");
                List<MemoryInfo> memoryInfos= memoryInfoImpl.getLastedMemoryInfo();
                for(MemoryInfo mem:memoryInfos){
                    System.out.println(mem.toString() + new Data() + "\n");
                    System.out.println("-----查询结束-----"+ "\n");
                    System.out.println("--向前端发送一次数据--"+ "\n");
                    // Object message = JSON.toJSON(memoryInfo); // 将Java对象转化为Json对象
                    // webSocket.getSession().getAsyncRemote().sendObject(message); // 发送Json对象
                    String message = JSON.toJSONString(mem); 
                    webSocket.getSession().getAsyncRemote().sendText(message); // 发送Json字符串
                    System.out.println("发送的数据为:"+ message + "\n");
                    System.out.println("---------数据发送结束------------"+ "\n");
                }
            }
        }else{
            System.out.println("***********WebSocket is closed!************");
        }
    }


    
    
    /**获取装填的数据
     * @return memoryInfo
     */
    public MemoryInfo getMemoryInfo(){
        return this.memoryInfo;
    }

  
}