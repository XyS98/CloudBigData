package com.sitech.cloudide;

import com.sitech.cloudide.utils.RedisClientUtils;
import redis.clients.jedis.JedisCluster;

public class RedisTest{


    public static void main(String[] args) {
    String servers = "172.18.231.108:9738";
    String password="Cn@aDm443";
    JedisCluster jedisCluster = RedisClientUtils.getInstance(servers, password);
    RedisClientUtils.setKey("yjbInfo", "未来之星学员", jedisCluster);
    System.out.println(RedisClientUtils.getKey("yjbInfo", jedisCluster));
    }
}