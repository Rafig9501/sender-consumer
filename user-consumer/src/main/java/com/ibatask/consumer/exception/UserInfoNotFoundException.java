package com.ibatask.consumer.exception;

public class UserInfoNotFoundException extends RuntimeException{

    public UserInfoNotFoundException(String message){
        super(message);
    }
}
