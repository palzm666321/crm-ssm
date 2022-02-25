package com.bjpowernode.mapper;


import com.bjpowernode.domain.TranHistory;

import java.util.List;

public interface TranHistoryMapper {

    int insert(TranHistory t);

    List<TranHistory> getTranHistoryByIdList(String tranId);
}
