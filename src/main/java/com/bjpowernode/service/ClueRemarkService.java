package com.bjpowernode.service;

import com.bjpowernode.domain.Activity;
import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.ClueActivityRelationVo;
import com.bjpowernode.domain.ClueRemark;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClueRemarkService {


    /**
     * 添加备注
     * @param clueRemark 要添加的备注
     * @return true成功，false失败
     */
    boolean addClueRemark(ClueRemark clueRemark);

    /**
     * 根据id查找备注
     * @param id 备注id
     * @return List
     */
    List<ClueRemark> getClueRemarkByIdAll(String id);




}
