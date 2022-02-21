package com.bjpowernode.exceptions;

public class ExpireTimeException extends LoginException {

    ExpireTimeException(){

    }

    public ExpireTimeException(String msg){
        super(msg);
    }

}
