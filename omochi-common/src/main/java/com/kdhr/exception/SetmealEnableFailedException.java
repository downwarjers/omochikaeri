package com.kdhr.exception;

/**
 * 套餐啟用失敗異常
 */
public class SetmealEnableFailedException extends BaseException {

    public SetmealEnableFailedException(){}

    public SetmealEnableFailedException(String msg){
        super(msg);
    }
}
