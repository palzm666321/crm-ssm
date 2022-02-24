package com.bjpowernode.service.impl;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.ClueActivityRelationVo;
import com.bjpowernode.domain.ClueRemark;
import com.bjpowernode.mapper.ActivityMapper;
import com.bjpowernode.mapper.ClueMapper;
import com.bjpowernode.mapper.ClueRemarkMapper;
import com.bjpowernode.service.ClueRemarkService;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.JSONObject;
import com.bjpowernode.utils.MD5Util;
import com.bjpowernode.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public boolean addClueRemark(ClueRemark clueRemark) {
        clueRemark.setId(UUIDUtil.getUUID());
        clueRemark.setCreateTime(DateTimeUtil.getSysTime());
        clueRemark.setEditFlag("0");
        return clueRemarkMapper.addClueRemark(clueRemark) == 1;
    }

    @Override
    public List<ClueRemark> getClueRemarkByIdAll(String id) {
        return clueRemarkMapper.getClueRemarkByIdAll(id);
    }


}



