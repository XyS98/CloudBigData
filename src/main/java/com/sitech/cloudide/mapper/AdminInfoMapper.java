package com.sitech.cloudide.mapper;

import java.util.List;

import com.sitech.cloudide.bean.AdminInfo;

import org.springframework.stereotype.Repository;


/**
 * @Author yangjb 数据接口层: 定义 service类通过mybatis访问数据库的抽象方法 具体实现在
 *         resource/mapper/AdminInfoMapper.xml中
 */
@Repository
public interface AdminInfoMapper {
    // 登录方法
    public AdminInfo login(AdminInfo af);

    // 注册方法
    public int singUp(AdminInfo af);

    // 查询所有用户信息的方法
    public List<AdminInfo> findAll();

    // 修改用户名的方法
    public int alterName(String oldName, String newName);

    // 获取手机号码的方法
    public String getPhone(String phone);

    // 通过ID查询所有信息
    public AdminInfo findById(int aid);
}
