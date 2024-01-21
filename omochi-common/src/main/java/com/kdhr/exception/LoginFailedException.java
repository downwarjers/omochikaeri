package com.kdhr.exception;

/**
 * 登陸失敗異常
 */
public class LoginFailedException extends BaseException{
    public LoginFailedException(String msg){
        super(msg);
    }
}
