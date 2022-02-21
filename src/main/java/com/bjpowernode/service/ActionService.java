package com.bjpowernode.service;

import com.bjpowernode.domain.User;
import com.bjpowernode.exceptions.LoginException;
import com.bjpowernode.exceptions.MyException;

public interface ActionService {

    User login(String loginAct, String loginPwd,String ip)throws LoginException;

}
