/*
 * @DescruserIdttion: 
 * @Author: yjb
 * @Date: 2020-08-04 13:32:02
 * @LastEditTime: 2020-08-04 23:19:23
 */
package com.sitech.cloudide.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSON;
import com.sitech.cloudide.job.GetMemoryInfoJob;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/webSocket/{userId}") // websocket扫描的URL
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<String, WebSocketServer>();
    private Session session;
    private String userId;  
    // private SpringCtxUtils springCtxUtils;

    private static GetMemoryInfoJob getMemoryInfoJob;
    // @Autowired
    // private static QuartzManagerUtil quartzManagerUtil;


    /**
     * 给其类提供获取SessionPools的方法
     * @return sessionPools
     */
    public static ConcurrentHashMap<String, WebSocketServer> getWebSocketServer(){
        return webSocketMap;
    } 

    public Session getSession(){
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


       /**
        * 将Object转化为Json数据发送
        * @param session
        * @param message 
        * @throws IOException
        */
       public void sendMessage(Session session, Object message) throws IOException {
        if(session != null){
            synchronized (session) {
//                System.out.println("发送数据：" + message);
                String mes = JSON.toJSONString(message);
                this.session.getAsyncRemote().sendText(mes);
            }
        }
    }

    
    //给指定用户发送信息
    public void sendInfo(String userId, String message){
        Session session = sessionPools.get(userId);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    
    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId){
        this.session = session;
        this.userId = userId;
        webSocketMap.put(userId, this); // this -->WebSocketServer
        addOnlineCount();
        try {
            sendMessage(session, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "userId") String userId){
        webSocketMap.remove(userId);
        subOnlineCount();
        System.out.println(userId + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException{
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }    

     /**
//      * @MethodName: setGetMemoryInfoJob
//      * @DescruserIdtion: 通过方法注入bean类
//      * @param getMemoryInfoJob
//      */
//     @Autowired
//     public void setGetMemoryInfoJob(GetMemoryInfoJob getMemoryInfoJob){
//         WebSocketServer.getMemoryInfoJob = getMemoryInfoJob;
//     }

//     //发送消息
//     public void sendMessage(Session session, String message) throws IOException {
//         if(session != null){
//             synchronized (session) {
// //                System.out.println("发送数据：" + message);
//                 this.session.getAsyncRemote().sendText(message);
//             }
//         }
//     }
}