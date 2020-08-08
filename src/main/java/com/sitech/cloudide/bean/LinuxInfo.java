/*
 * @Descripttion: Linxu主机信息
 * @Author: yjb
 * @Date: 2020-07-30 14:31:05
 * @LastEditTime: 2020-08-07 14:23:13
 */ 
package com.sitech.cloudide.bean;

import lombok.Data;

@Data
public class LinuxInfo {

    private int linux_id;
    private String linux_name;
    private String linux_ip;
    private int linux_port;
    private String linux_userName;
    private String linux_passward;
    private String tips;

    public LinuxInfo() {
    }

    public LinuxInfo(int linux_id, String linux_name, String linxu_ip,int linux_port, String linux_userName, String linux_passward,
            String tips) {
        this.linux_id = linux_id;
        this.linux_name = linux_name;
        this.linux_ip = linxu_ip;
        this.linux_port = linux_port;
        this.linux_userName = linux_userName;
        this.linux_passward = linux_passward;
        this.tips = tips;
    }

    

   
}