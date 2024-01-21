package com.kdhr.exception;

/**
 * 密碼修改錯誤異常
 */
public class PasswordEditFailedException extends BaseException{

    public PasswordEditFailedException(String msg){
        super(msg);
    }

}
