package com.bjpowernode.service.impl;

import com.bjpowernode.domain.ClueActivityRelation;
import com.bjpowernode.domain.ClueActivityRelationVo;
import com.bjpowernode.mapper.ActivityMapper;
import com.bjpowernode.mapper.ClueActivityRelationMapper;
import com.bjpowernode.service.ClueActivityRelationService;
import com.bjpowernode.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public boolean addClueActivityRelation(String cid,String param) {
        boolean flag=true;
        String arr[]=param.split(",");
        for (int i=0;i<arr.length;i++){
            ClueActivityRelation c=new ClueActivityRelation();
            c.setId(UUIDUtil.getUUID());
            c.setClueId(cid);
            c.setActivityId(arr[i]);
            if (clueActivityRelationMapper.addClueActivityRelation(c)!=1){
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public List<ClueActivityRelationVo> getRelationAll(String id) {
        return activityMapper.getRelationAll(id);
    }

    @Override
    public boolean remove(String id) {
        return activityMapper.remove(id) == 1;
    }

    @Override
    public List<ClueActivityRelationVo> getRelationLikeAll(String id, String name) {
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("id",id);
        return activityMapper.getRelationLikeAll(map);
    }
}



