/*
 * @Descripttion:Linux主机内存信息的数据服务实现类
 *                   Linux命令与返回信息格式详见MemoryInfo.java
 * @Author: yjb
 * @Date: 2020-08-01 13:38:59
 * @LastEditTime: 2020-08-06 13:56:13
 */ 
package com.sitech.cloudide.service.impl;

import java.io.IOException;
import java.util.List;
import com.sitech.cloudide.bean.MemoryInfo;
import com.sitech.cloudide.mapper.MemoryInfoMapper;
import com.sitech.cloudide.service.MemoryInfoService;
import com.sitech.cloudide.utils.MemoryAnalysis;
import com.sitech.cloudide.utils.RemoteShellExcuteUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryInfoImpl implements MemoryInfoService {

    @Autowired
    private MemoryInfoMapper memoryInfomMapper;
    @Autowired
    private MemoryAnalysis memoryAnalysis;
    @Autowired
    private RemoteShellExcuteUtil remoteShellExcuteUtil;

    /**
     * @MethodName: insertMemoryInfo
     * @Descripttion: 将解析好的Linxu Memory信息存入数据库
     * @param {List<MemoryInfo>} 存储两个MemoryInfo对象的List,一个存放mem的信息，另一个存放Swap的信息
     * @return: 1 插入成功;0 插入失败
     * @Author: yjb
     * @version: 1.0
     * @Date: 2020-08-01 14:24:34
     */
    @Override
    public int insertMemoryInfo(List<MemoryInfo> memoryInfos) {
        return memoryInfomMapper.insertMemoryInfo(memoryInfos);
    }

    /**
     * @MethodName: selectMemoryInfos
     * @Descripttion: 根据时间查询Linxu的Memory信息(mem+Swap）
     * @param {List<MemoryInfo>}
     * @return:
     * @Author: yjb
     * @version:
     * @Date: 2020-08-01 14:28:15
     */
    @Override
    public List<MemoryInfo> selectMemoryInfos() {
        return memoryInfomMapper.selectMemoryInfos();
    }

    
    @Override
    public MemoryInfo selectMemoryInfo() {
        return memoryInfomMapper.selectMemoryInfo();
    }

    /**
     * @MethodName: getMemoryInfos 
     * @Descripttion: 获取并处理Linux主机内存信息
     * @param {String}
     *          ip:     Linxu ip 默认 port
     * @param {String}
     *          username:  Linux 用户名
     * @param {String}  
     *          password:  Linux 用户的密码 
     * @param {String}
     *          command: free - m (查询命令)
     * @return {List<MemoryInfo>} 
     * @Author: yjb
     * @version: 
     * @Date: 2020-08-03 15:24:26
     */
    @Override
    public List<MemoryInfo> getMemoryInfos(String ip, String username, String password, String command,int linux_id)
            throws IOException {
        remoteShellExcuteUtil.getLinuxInfo(ip, username, password);
        String  runInfo = remoteShellExcuteUtil.exec(command);
        return memoryAnalysis.analysisMemory(runInfo,linux_id);
    }

    @Override
    public List<MemoryInfo> getLastedMemoryInfo() {
        return memoryInfomMapper.getLastedMemoryInfo();
    }
}