package com.bjpowernode.service.impl;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.User;
import com.bjpowernode.mapper.ClueMapper;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.JSONObject;
import com.bjpowernode.utils.MD5Util;
import com.bjpowernode.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.json.JsonObject;
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

    @Override
    public List<Clue> getAll() {
        return clueMapper.getAll(new Clue());
    }

    @Override
    public String getClueById(String id) {
        return JSONObject.JsonObj(clueMapper.getClueById(id));
    }

    @Override
    public boolean updateClue(Clue clue) {
        clue.setEditTime(DateTimeUtil.getSysTime());
        return clueMapper.updateClue(clue) == 1;
    }

    @Override
    public boolean deleteClueById(String id) {
        return clueMapper.deleteClue(id) == 1;
    }

    @Override
    public PageInfo<Clue> splitPage(Clue clue,Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Clue> movies = clueMapper.getAll(clue);
        PageInfo<Clue> pageInfo = new PageInfo<> (movies);
        return pageInfo;
    }
}
