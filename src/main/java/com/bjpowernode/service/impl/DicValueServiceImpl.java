package com.bjpowernode.service.impl;

import com.bjpowernode.domain.DicType;
import com.bjpowernode.domain.DicValue;
import com.bjpowernode.mapper.DicTypeMapper;
import com.bjpowernode.mapper.DicValueMapper;
import com.bjpowernode.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;

    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Override
    public Map<String,List<DicValue>> getAll() {

        Map<String,List<DicValue>> map=new HashMap<>();

        List<DicType> TList=dicTypeMapper.getAll();

        TList.forEach(i -> map.put(i.getCode(),dicValueMapper.getByCodeList(i.getCode())));

        return map;
    }
}
