package com.bjpowernode.service;

import com.bjpowernode.domain.ClueActivityRelation;
import com.bjpowernode.domain.ClueActivityRelationVo;
import com.bjpowernode.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface ClueActivityRelationService {
    /**
     * 添加关联表
     * @param cid 线索id
     * @param param 需要关联的市场字符串
     * @return true成功，false失败
     */
   boolean addClueActivityRelation(String cid,String param);


    /**
     * 查找当前线索关联的市场活动表
     * @param id 线索id
     * @return list
     */
   List<ClueActivityRelationVo> getRelationAll(String id);


   boolean remove(String id);

    /**
     * 关联市场活动模糊查询
     * @param id 线索表id
     * @param name 模糊查询关键字
     * @return list
     */
    List<ClueActivityRelationVo> getRelationLikeAll(String id, String name);

}
