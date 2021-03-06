package com.bjpowernode.mapper;

import com.bjpowernode.domain.ClueActivityRelation;
import com.bjpowernode.domain.ClueActivityRelationVo;

import java.util.List;
import java.util.Map;

public interface ClueActivityRelationMapper {

    List<ClueActivityRelation> selectByClueId(String clueId);

    int addClueActivityRelation(ClueActivityRelation clueActivityRelation);

    int delete(ClueActivityRelation clueActivityRelation);
}
