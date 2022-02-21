package com.bjpowernode.exceptions;

public class UserNullException extends LoginException {

    UserNullException(){

    }

    public UserNullException(String msg){
        super(msg);
    }

}
