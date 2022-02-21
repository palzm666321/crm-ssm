package com.bjpowernode.exceptions;

public class UserIpException extends LoginException {

    UserIpException(){

    }

    public UserIpException(String msg){
        super(msg);
    }

}
