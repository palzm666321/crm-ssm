package com.bjpowernode.service.impl;

import com.bjpowernode.domain.User;
import com.bjpowernode.exceptions.*;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.service.ActionService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
