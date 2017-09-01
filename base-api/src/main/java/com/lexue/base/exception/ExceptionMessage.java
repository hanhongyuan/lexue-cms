package com.lexue.base.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.TimeoutException;


/**
 * RESTful服务返回的错误信息
 * <P>
 */
public class ExceptionMessage implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionMessage.class);
    private static final long serialVersionUID = 2640926329092743174L;
    // 统一错误码
    private int code;
    // 错误信息摘要
    private String message;
    // 错误信息详情（主要用于调试）
    private String detail = "";

    public ExceptionMessage() {
        super();
    }

    public ExceptionMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionMessage(int code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public ExceptionMessage(ExceptionCode errorCode) {
        this.code = errorCode.value();
        this.message = errorCode.msg();
    }

    public ExceptionMessage(ExceptionCode errorCode, String detail) {
        this.code = errorCode.value();
        this.message = errorCode.msg();
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static ExceptionMessage parse(Exception e) {
        String err = e.getMessage();
        try {
            if (e instanceof TimeoutException) {
                return new ExceptionMessage(ExceptionCode.SYS_ERROR_RPC_CONNECTION, "远程服务调用超时");
            } else if(e instanceof com.netflix.hystrix.exception.HystrixRuntimeException){
                return new ExceptionMessage(ExceptionCode.SYS_ERROR_NOT_CLIENT,"远程服务调用失败");
            }else if(e instanceof feign.RetryableException){
                return new ExceptionMessage(ExceptionCode.SYS_ERROR_RPC_CONNECTION,"远程服务调用失败");
            } else{
                return JSON.parseObject(err.split("content:")[1], ExceptionMessage.class);
            }
        } catch (Exception e1) {
            logger.error("从异常信息中解析ErrorMsg失败", e1);
            return new ExceptionMessage(ExceptionCode.SYS_ERROR, err);
        }
    }
}
