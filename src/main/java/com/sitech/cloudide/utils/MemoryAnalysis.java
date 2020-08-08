/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-30 14:17:33
 * @LastEditTime: 2020-08-06 14:53:40
 */
package com.sitech.cloudide.utils;
/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-30 14:17:33
 * @LastEditTime: 2020-07-30 21:04:27
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sitech.cloudide.bean.MemoryInfo;

import org.springframework.stereotype.Component;

@Component
public class MemoryAnalysis {

  public MemoryAnalysis() {

  }

  /**
   * @name: analysisMemory
   * @msg: 分析处理主机信息
   * @param {String}
   * @return: List<MemoryInfo> 2个MemoryInfo对象
   * @throws IOException
   */
  public List<MemoryInfo> analysisMemory(String runInfo,int linux_id) throws IOException {
    // System.out.print(runInfo + "\n");
    List<MemoryInfo> memoryInfoList = new ArrayList<MemoryInfo>();
    MemoryInfo memoryInfo = new MemoryInfo();
    MemoryInfo swapInfo = new MemoryInfo();
    // 根据 “:” 冒号第一次切割，3份
    String mes[] = runInfo.split(":", 3);
    System.out.println("*******使用Shell工具从Linux获取的信息*******" + "\n");
    for (String i : mes) {
      System.out.println(i.trim() + "\n");
    }
    // System.out.println("mes.length:" + mes.length);
    // 舍弃第一个字符串数组，将剩下两个字符串数组依据空格切分

    String[] mem = mes[1].split("\\s+", 7);
    String[] Swap = mes[2].split("\\s+", 7);

    // 去除 1374Swap
    String alter = mem[6];
    int numEnd = alter.indexOf("Swap");
    String numOnly = alter.substring(0, numEnd);
    mem[6] = numOnly;
    int[] memNum = new int[mem.length - 1];
    int[] SwapNum = new int[Swap.length - 1];
    // 转换为int 从1开始，去除memNum[0]的空格
    for (int i = 1; i < mem.length; i++) {
      memNum[i - 1] = Integer.parseInt(mem[i]);
    }
    for (int i = 1; i < Swap.length; i++) {
      SwapNum[i - 1] = Integer.parseInt(Swap[i]);
    }
    
    Date date = new Date();
    long time = date.getTime();
    String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);

    memoryInfo.setLinux_id(linux_id);
    memoryInfo.setTotal(memNum[0]);
    memoryInfo.setMemkid(1); // 添加信息种类 1 mem
    memoryInfo.setUsed(memNum[1]);
    memoryInfo.setFree(memNum[2]);
    memoryInfo.setShared(memNum[3]);
    memoryInfo.setBuffORcache(memNum[4]);
    memoryInfo.setAvailable(memNum[5]);
    memoryInfo.setSelectedTime(currentTime);
    memoryInfoList.add(memoryInfo);
    System.out.println("~~~~~~~~runInfo解析并存储到MemoryInfo对象~~~~~~~~"+ "\n");
    System.out.println(memoryInfo.toString());

    // 添加
    // swapInfo.setLinux_id(Linux_id);
    swapInfo.setLinux_id(linux_id);
    swapInfo.setTotal(SwapNum[0]);
    swapInfo.setMemkid(2); // 添加信息种类 2 Swap
    swapInfo.setUsed(SwapNum[1]);
    swapInfo.setFree(SwapNum[2]);
    swapInfo.setShared(0);
    swapInfo.setBuffORcache(0);
    swapInfo.setAvailable(0);
    swapInfo.setSelectedTime(currentTime);
    memoryInfoList.add(swapInfo);
    System.out.println(swapInfo.toString() + "\n");

    return memoryInfoList;
  }
}
 