package com.sitech.cloudide.bean;

import java.util.Objects;

public class AdminInfo {

    private int aid;
    private String aname;
    private String phone;
    private String pwd;

    public AdminInfo(int aid, String aname, String phone, String pwd) {
        this.aid = aid;
        this.aname = aname;
        this.phone = phone;
        this.pwd = pwd;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminInfo adminInfo = (AdminInfo) o;
        return aid == adminInfo.aid &&
                Objects.equals(aname, adminInfo.aname) &&
                Objects.equals(phone, adminInfo.phone) &&
                Objects.equals(pwd, adminInfo.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, aname, phone, pwd);
    }
}