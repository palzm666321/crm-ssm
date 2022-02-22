package com.bjpowernode.service;

import com.bjpowernode.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicValueService {

    Map<String,List<DicValue>> getAll();

}
