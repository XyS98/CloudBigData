/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-30 18:41:03
 * @LastEditTime: 2020-08-01 14:34:47
 */ 
package com.sitech.cloudide.bean;

/**
 * 
 * <p>Title: MachineMemInfo</p>
 * <p>Description: 主机内存信息实体类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 思特奇 </p>
 * @author heweia
 * @version 1.0
 * @createtime 2019-4-9 上午9:11:21
 *
 */
public class MachineMemInfo {
    private String ip;
    private long applyMem;
    private long usedMem;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getApplyMem() {
        return applyMem;
    }

    public void setApplyMem(long applyMem) {
        this.applyMem = applyMem;
    }

    public long getUsedMem() {
        return usedMem;
    }

    public void setUsedMem(long usedMem) {
        this.usedMem = usedMem;
    }

    public long getLockedMem() {
        return this.applyMem - this.usedMem;
    }
}
