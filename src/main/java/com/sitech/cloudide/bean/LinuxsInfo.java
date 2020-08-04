/*
 * @Descripttion: Linxu主机信息
 * @Author: yjb
 * @Date: 2020-07-30 14:31:05
 * @LastEditTime: 2020-08-02 17:20:18
 */ 
package com.sitech.cloudide.bean;

import lombok.Data;

@Data
public class LinuxsInfo {

    private String linux_id;
    private String linux_name;
    private String linxu_ip;
    private String userName;
    private String linux_pwd;
    private String tips;

    public LinuxsInfo() {
    }

    public LinuxsInfo(String linux_id, String linux_name, String linxu_ip, String userName, String linux_pwd,
            String tips) {
        this.linux_id = linux_id;
        this.linux_name = linux_name;
        this.linxu_ip = linxu_ip;
        this.userName = userName;
        this.linux_pwd = linux_pwd;
        this.tips = tips;
    }

    public String getLinux_id() {
        return linux_id;
    }

    public void setLinux_id(String linux_id) {
        this.linux_id = linux_id;
    }

    public String getLinux_name() {
        return linux_name;
    }

    public void setLinux_name(String linux_name) {
        this.linux_name = linux_name;
    }

    public String getLinxu_ip() {
        return linxu_ip;
    }

    public void setLinxu_ip(String linxu_ip) {
        this.linxu_ip = linxu_ip;
    }

    public String getUserNanme() {
        return userName;
    }

    public void setUserNanme(String userName) {
        this.userName = userName;
    }

    public String getLinux_pwd() {
        return linux_pwd;
    }

    public void setLinux_pwd(String linux_pwd) {
        this.linux_pwd = linux_pwd;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((linux_id == null) ? 0 : linux_id.hashCode());
        result = prime * result + ((linux_name == null) ? 0 : linux_name.hashCode());
        result = prime * result + ((linux_pwd == null) ? 0 : linux_pwd.hashCode());
        result = prime * result + ((linxu_ip == null) ? 0 : linxu_ip.hashCode());
        result = prime * result + ((tips == null) ? 0 : tips.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        LinuxsInfo other = (LinuxsInfo) obj;
        if (linux_id == null) {
            if (other.linux_id != null)
                return false;
        } else if (!linux_id.equals(other.linux_id))
            return false;
        if (linux_name == null) {
            if (other.linux_name != null)
                return false;
        } else if (!linux_name.equals(other.linux_name))
            return false;
        if (linux_pwd == null) {
            if (other.linux_pwd != null)
                return false;
        } else if (!linux_pwd.equals(other.linux_pwd))
            return false;
        if (linxu_ip == null) {
            if (other.linxu_ip != null)
                return false;
        } else if (!linxu_ip.equals(other.linxu_ip))
            return false;
        if (tips == null) {
            if (other.tips != null)
                return false;
        } else if (!tips.equals(other.tips))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LinuxsInfo [linux_id=" + linux_id + ", linux_name=" + linux_name + ", linux_pwd=" + linux_pwd
                + ", linxu_ip=" + linxu_ip + ", tips=" + tips + ", userName=" + userName + "]";
    }

    
}