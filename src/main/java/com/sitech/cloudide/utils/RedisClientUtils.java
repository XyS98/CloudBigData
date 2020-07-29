package com.sitech.cloudide.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisClientUtils {
	/**
	 * 
	 * @Title: getInstance
	 * @Description: 初始化jedisCluster连接池加密码
	 * @param servers
	 * @param password
	 * @return JedisCluster
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 下午1:03:22
	 */
	public static synchronized JedisCluster getInstance(String servers,String password){

		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		
		if (null != servers && !"".equals(servers)) {
			String[] nodesArray = servers.split(",");
			if (null != nodesArray && nodesArray.length > 0) {
				for (int i = 0; i < nodesArray.length; i++) {
					String ipPort = nodesArray[i];
					String[] ipPortArray = ipPort.split(":");
					if (null != ipPortArray && ipPortArray.length == 2) {
						jedisClusterNodes.add(new HostAndPort(ipPortArray[0], Integer.parseInt(ipPortArray[1])));
					}
				}
			}
			System.out.println("host&port"+jedisClusterNodes.toString());
		}
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(1000);

		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 1000, 1000, 1, password,poolConfig);
		return jedisCluster;
	}
	
	
	/**
	 * 
	 * @Title: getInstance
	 * @Description: 初始化jedisCluster连接池不加密码
	 * @param servers
	 * @return
	 * @return JedisCluster
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-3 下午6:21:24
	 */
	public static synchronized JedisCluster getInstance(String servers){

		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		
		if (null != servers && !"".equals(servers)) {
			String[] nodesArray = servers.split(",");
			if (null != nodesArray && nodesArray.length > 0) {
				for (int i = 0; i < nodesArray.length; i++) {
					String ipPort = nodesArray[i];
					String[] ipPortArray = ipPort.split(":");
					if (null != ipPortArray && ipPortArray.length == 2) {
						jedisClusterNodes.add(new HostAndPort(ipPortArray[0], Integer.parseInt(ipPortArray[1])));
					}
				}
			}
		}
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(1000);

		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 1000, 1000, 1,poolConfig);
		return jedisCluster;
	}
	
	/**
	 * 
	 * @Title: setKey
	 * @Description: 设置key值
	 * @param key
	 * @param value
	 * @param jedisCluster
	 * @return void
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-3 下午6:20:34
	 */
	public static void setKey(String key,String value,JedisCluster jedisCluster){
		 jedisCluster.set(key, value);
	}
	
	/**
	 * 
	 * @Title: setNx
	 * @Description: setnx,如果key存在，返回0，如果不存在，则设置成功
	 * @param key
	 * @param value
	 * @param jedisCluster
	 * @return long
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:35:10
	 */
	public static long setNx(String key,String value,JedisCluster jedisCluster){
		return jedisCluster.setnx(key, value);
	}
	

	/**
	 * 
	 * @Title: setKey
	 * @Description: 添加超时时间，单位秒
	 * @param key
	 * @param value
	 * @param time
	 * @param jedisCluster
	 * @return String
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:20:51
	 */
	public static String setKey(String key,String value,int time ,JedisCluster jedisCluster){
		 return jedisCluster.setex(key, time,value);
	}
	
	/**
	 * 
	 * @Title: getKey
	 * @Description: 获取key值
	 * @param key
	 * @param jedisCluster
	 * @return String
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-3 下午6:20:01
	 */
	public static String getKey(String key,JedisCluster jedisCluster){
		return jedisCluster.get(key);
	}
	
	/**
	 * 
	 * @Title: getSet
	 * @Description: getset:设置key值，并返回旧值
	 * @param key
	 * @param jedisCluster
	 * @return String
	 * @return String
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:40:40
	 */
	public static String getSet(String key,String value,JedisCluster jedisCluster){
		return  jedisCluster.getSet(key,value);
	}
	
	/**
	 * 
	 * @Title: append
	 * @Description: 键值的填充
	 * @param key
	 * @param value
	 * @param jedisCluster
	 * @return long
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:52:53
	 */
	public static long append(String key,String value,JedisCluster jedisCluster){
		return  jedisCluster.append(key,value);
	}
	/**
	 * 
	 * @Title: hset
	 * @Description: 在缓存里写入hash key
	 * @param key
	 * @param field
	 * @param value
	 * @param jedisCluster
	 * @return long
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:52:29
	 */
	public static long hset(String key,String field,String value,JedisCluster jedisCluster){
		return  jedisCluster.hset(key,field,value);
	}
	
	/**
	 * 
	 * @Title: hexists
	 * @Description: 判断hash key 键值是否存在
	 * @param key
	 * @param field
	 * @param jedisCluster
	 * @return Boolean
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:51:49
	 */
	public static Boolean hexists(String key,String field,JedisCluster jedisCluster){
		return  jedisCluster.hexists(key,field);
	}
	
	/**
	 * 
	 * @Title: hkeys
	 * @Description: 获取hash key 值
	 * @param key
	 * @param jedisCluster
	 * @return Set<String>
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:51:08
	 */
	public static Set<String> hkeys(String key,JedisCluster jedisCluster){
		return  jedisCluster.hkeys(key);
	}

	/**
	 * 
	 * @Title: hdel
	 * @Description: 删除hash key
	 * @param key
	 * @param field
	 * @param jedisCluster
	 * @return long
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:59:38
	 */
	public static long hdel(String key,String field,JedisCluster jedisCluster){
		return  jedisCluster.hdel(key,field);
	}
	
	/**
	 * 
	 * @Title: hgetAll
	 * @Description: 获取所有的hash key值
	 * @param key
	 * @param jedisCluster
	 * @return
	 * @return Map<String,String>
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-4 上午10:59:58
	 */
	public static Map<String,String> hgetAll(String key,JedisCluster jedisCluster){
		return  jedisCluster.hgetAll(key);
	}

	/**
	 * 
	 * @Title: deleteKey
	 * @Description: 删除key
	 * @param key
	 * @param jedisCluster
	 * @return void
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-3 下午6:19:09
	 */
	public static void deleteKey(String key,JedisCluster jedisCluster){
	   jedisCluster.del(key);
	}
	/**
	 * 
	 * @Title: close
	 * @Description: 关闭jedis连接
	 * @param jedisCluster
	 * @return void
	 * @author heweia
	 * @version 1.0
	 * @createtime 2018-1-3 下午6:19:36
	 */
	public static void close(JedisCluster jedisCluster){
		
		if (jedisCluster != null)
			try {
				jedisCluster.close();
			} catch (Exception ex) {
			}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String usedKey = "yjb";
//		String password = "e3base";
//		不加密码测试
//		String server = "192.168.228.136:6400,192.168.228.138:6401,192.168.228.138:6400,192.168.228.139:6401,192.168.228.139:6400,192.168.228.136:6401";
//		加密码测试

		/** 腾讯云
		 * String server = "42.194.201.179:7002";
		 *	String password = "e3base";
		 */

		/** 本地主机
		 * String server = "192.168.6.131:7001";
		 *	String password = "e3base";
		 */

		//老师的远程redis
		String server = "172.18.231.108:9738";
		String password = "Cn@aDm443";
		
		
		
//		String server = "127.16.0.12:7001";
//		String server = "192.168.6.131:8379";
		JedisCluster jedisCluster = RedisClientUtils.getInstance(server,password);
		
        System.out.println("setkey方法验证:"+jedisCluster.set(usedKey, "yjb@si-tech.com.cn"));
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));
        System.out.println("setnx方法验证:"+jedisCluster.setnx(usedKey, "yjb@si-tech.com.cn"));//key不存在，返回值为1
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));

        System.out.println("setnx方法验证:"+jedisCluster.setnx(usedKey, "yjb@si-tech.com.cn"));//已经存在，返回值为0
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));
        System.out.println("setkey方法验证:"+jedisCluster.set(usedKey, "1072265056@qq.com"));
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));//结果：412401889@qq.com

        //从offset=8开始替换字符串value的值
        System.out.println("setrange方法验证:"+jedisCluster.setrange(usedKey, 8, "abc"));
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));

        System.out.println("setrange方法验证:"+jedisCluster.setrange(usedKey, 8, "abcdefghhhhhh"));
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));

        //查询子串,返回startOffset到endOffset的字符
        System.out.println("getrange方法验证:"+jedisCluster.getrange(usedKey, 2, 5));//结果：2026
        
        System.out.println("append方法验证:"+jedisCluster.append(usedKey, "aa"));
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));
        System.out.println("append方法验证:"+jedisCluster.append(usedKey, "yjb"));
        System.out.println("getkey方法验证:"+jedisCluster.get(usedKey));
        
        String hashKey = "hashKey";
        jedisCluster.del(hashKey);

        System.out.println("hset方法验证:"+jedisCluster.hset(hashKey, "program_yjb", "111"));
        System.out.println("hexists方法验证:"+jedisCluster.hexists(hashKey, "program_yjb"));
        System.out.println("hset方法验证:"+jedisCluster.hset(hashKey, "program_yjb", "222"));

        System.out.println("hset方法验证:"+jedisCluster.hset(hashKey, "program_yjb", "333"));
        System.out.println("hset方法验证:"+jedisCluster.hset(hashKey, "program_yjb", "444"));

        System.out.println("hkeys方法验证:"+ jedisCluster.hkeys(hashKey));

        System.out.println("hgetAll方法验证:"+jedisCluster.hgetAll(hashKey));
        System.out.println("hincrBy方法验证:"+jedisCluster.hincrBy(hashKey, "program_yjb", 2));
        System.out.println("hmget方法验证:"+jedisCluster.hmget(hashKey, "program_yjb", "program_yjb"));

        jedisCluster.hdel(hashKey, "program_hw");
        System.out.println("hgetAll方法验证:"+jedisCluster.hgetAll(hashKey));
        
	}

}
