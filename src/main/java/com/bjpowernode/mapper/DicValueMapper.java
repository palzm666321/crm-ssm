package com.bjpowernode.mapper;

import com.bjpowernode.domain.DicValue;

import java.util.List;

public interface DicValueMapper {

    List<DicValue> getByCodeList(String code);

}
