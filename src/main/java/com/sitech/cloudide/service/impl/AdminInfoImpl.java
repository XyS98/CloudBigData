package com.sitech.cloudide.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sitech.cloudide.bean.AdminInfo;
import com.sitech.cloudide.mapper.AdminInfoMapper;
import com.sitech.cloudide.service.AdminInfoService;

/**
 * @Author yangjb
 * 实现 AdminInfo 实体类的服务
 */
@Service //定义一个服务类
public class AdminInfoImpl extends BaseDaoImpl implements AdminInfoService {
  @Autowired 
  private AdminInfoMapper mapper; // 自动注入 AdminInfoMapper 数据接口抽象给方法类

    @Override
    public AdminInfo login(AdminInfo af) {
        return mapper.login(af);
    }

    @Override
    public int singUp(AdminInfo af) {
        return mapper.singUp(af);
    }

    @Override
    public List<AdminInfo> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public int alterName(String oldName, String newName) {
        return mapper.alterName(oldName, newName);
    }
}
