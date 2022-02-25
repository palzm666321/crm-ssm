package com.bjpowernode.mapper;

import com.bjpowernode.domain.ClueRemark;
import com.bjpowernode.domain.DicValue;

import java.util.List;

public interface ClueRemarkMapper {

    int addClueRemark(ClueRemark clueRemark);
    List<ClueRemark> getClueRemarkByIdAll(String id);

    int delete(ClueRemark clueRemark);
}
