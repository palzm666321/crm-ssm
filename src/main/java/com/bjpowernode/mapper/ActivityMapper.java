package com.bjpowernode.mapper;

import com.bjpowernode.domain.Activity;
import com.bjpowernode.domain.ClueActivityRelationVo;
import com.bjpowernode.domain.DicType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {

    List<ClueActivityRelationVo> getRelationLikeAll(Map<String,Object> map);

    List<ClueActivityRelationVo> getRelationAll(String id);

    int remove(String id);
}
