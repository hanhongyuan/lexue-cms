package com.lexue.base.exception;

/**
 * 登录验证失败异常
 */
public class LoginException extends Exception{

    public LoginException(){
        super("登录验证失败");
    }
}
