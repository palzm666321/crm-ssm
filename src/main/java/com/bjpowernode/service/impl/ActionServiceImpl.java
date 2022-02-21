package com.bjpowernode.service.impl;

import com.bjpowernode.domain.User;
import com.bjpowernode.exceptions.*;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.service.ActionService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String loginAct, String loginPwd,String ip)throws LoginException{

        Map<String,String> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd", MD5Util.getMD5(loginPwd));
        User user=userMapper.getByUser(map);
        if (user == null){
            throw new UserNullException("用户名或者密码不正确");
        }
        if (DateTimeUtil.getSysTime().compareTo(user.getExpireTime())>0){
            throw new ExpireTimeException("用户已过期，请联系管理员");
        }
        if ("0".equals(user.getLockState())){
            throw new LockStateException("用户已冻结，请联系管理员");
        }
        if (!user.getAllowIps().contains(ip)){
            throw new UserIpException("没有访问权限，请联系管理员");
        }

        return user;
    }


}
