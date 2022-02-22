package com.bjpowernode.service.impl;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.User;
import com.bjpowernode.mapper.ClueMapper;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.MD5Util;
import com.bjpowernode.utils.UUIDUtil;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;


    @Override
    public boolean addClue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        return clueMapper.addClue(clue) == 1;
    }
}
