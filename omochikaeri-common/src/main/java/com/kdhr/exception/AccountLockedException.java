package com.kdhr.exception;

/**
 * 帳號被鎖定異常
 */
public class AccountLockedException extends BaseException {

    public AccountLockedException() {
    }

    public AccountLockedException(String msg) {
        super(msg);
    }

}
