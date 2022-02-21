package com.bjpowernode.exceptions;

public class LockStateException extends LoginException {

    LockStateException(){

    }

    public LockStateException(String msg){
        super(msg);
    }

}
