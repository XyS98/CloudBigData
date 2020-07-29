package com.sitech.cloudide.service;


import com.sitech.cloudide.bean.AdminInfo;
import java.util.List;

/**
        * @Author yangjb
        * 定义 AdminInfo 相关的数据服务抽象方法
        */

public interface AdminInfoService {

    // 定义一个用户登录的数据服务抽象方法
    public AdminInfo login(AdminInfo af);

    // 注册 的抽象方法
    public int singUp(AdminInfo af);

    // 查询所有用户信息的抽象方法
    public List<AdminInfo> findAll();

    // 修改用户名的方法
    public int alterName(String oldName, String newName);

    // 获取手机号码
    public String getPhone(String phone);

    // 通过ID查询所有信息
    public AdminInfo findById(int aid);
}
