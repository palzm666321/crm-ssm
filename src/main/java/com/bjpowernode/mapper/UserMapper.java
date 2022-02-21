package com.bjpowernode.mapper;

import com.bjpowernode.domain.User;

import java.util.Map;

public interface UserMapper {

    User getByUser(Map<String,String> map);

}
