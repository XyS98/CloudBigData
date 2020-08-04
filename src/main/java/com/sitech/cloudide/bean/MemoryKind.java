/*
 * @Descripttion: 
 * @Author: yjb
 * @Date: 2020-07-29 17:30:06
 * @LastEditTime: 2020-08-02 17:37:11
 */ 
package com.sitech.cloudide.bean;

public class MemoryKind {

    private int memkid; //   1 mem --> 物理内存 2 Swap--> 硬盘交换分区
    private String memoryKind;//

    public MemoryKind() {
    }

    public MemoryKind(int memkid, String memoryKind) {
        this.memkid = memkid;
        this.memoryKind = memoryKind;
    }

    public int getMemkid() {
        return memkid;
    }

    public void setMemkid(int memkid) {
        this.memkid = memkid;
    }

    public String getMemoryKind() {
        return memoryKind;
    }

    public void setMemoryKind(String memoryKind) {
        this.memoryKind = memoryKind;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + memkid;
        result = prime * result + ((memoryKind == null) ? 0 : memoryKind.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MemoryKind other = (MemoryKind) obj;
        if (memkid != other.memkid)
            return false;
        if (memoryKind == null) {
            if (other.memoryKind != null)
                return false;
        } else if (!memoryKind.equals(other.memoryKind))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemoryKind [memkid=" + memkid + ", memoryKind=" + memoryKind + "]";
    }

    
    
}