package com.bjpowernode.mapper;


import com.bjpowernode.domain.Activity;
import com.bjpowernode.domain.Contacts;

import java.util.List;

public interface ContactsMapper {

    int insert(Contacts contacts);

    List<Activity> getContactsLikeList(String name);
}
