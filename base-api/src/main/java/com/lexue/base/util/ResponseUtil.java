package com.lexue.base.util;

import com.lexue.base.exception.ExceptionCode;
import com.lexue.base.exception.ExceptionMessage;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * HTTP响应工具类
 * <P>
 */
public class ResponseUtil {
    private ResponseUtil() {}

    /**
     * 一般HTTP响应
     *
     * @param status HTTP响应状态码
     * @param obj    返回的对象
     * @return
     */
    public static Response response(int status, Object obj) {
        return Response.status(status).entity(obj)
        		.type(MediaType.APPLICATION_JSON)
        		.header("Access-Control-Allow-Origin","*")
        		.build();
    }

    /**
     * 一般HTTP响应
     *
     * @param entity ResponseEntity对象
     * @return
     */
    public static Response response(ResponseEntity<?> entity) {
        return Response.status(entity.getStatusCode().value()).entity(entity.getBody())
        		.type(MediaType.APPLICATION_JSON)
        		.header("Access-Control-Allow-Origin","*")
                .build();
    }

    /**
     * 成功的HTTP响应
     *
     * @param obj
     * @return
     */
    public static Response okResponse(Object obj) {
        return Response.ok(obj)
        		.type(MediaType.APPLICATION_JSON)
        		.header("Access-Control-Allow-Origin","*")
        		.build();
    }

    /**
     * 业务错误的HTTP响应
     *
     * @param code 统一错误码
     * @param msg  错误摘要信息
     * @return
     */
    public static Response exceptionResponse(int code, String msg) {
        return assemblyResponse(ExceptionCode.BIZ_ERROR, new ExceptionMessage(code, msg));
    }

    /**
     * 业务错误的HTTP响应
     *
     * @param code   统一错误码
     * @param msg    错误摘要信息
     * @param detail 错误详细信息（一般用于调试）
     * @return
     */
    public static Response exceptionResponse(int code, String msg, String detail) {
        return assemblyResponse(ExceptionCode.BIZ_ERROR, new ExceptionMessage(code, msg, detail));
    }

    /**
     * 业务错误的HTTP响应
     *
     * @param errorCode 异常码对象
     * @return
     */
    public static Response exceptionResponse(ExceptionCode errorCode) {
        return assemblyResponse(ExceptionCode.BIZ_ERROR, new ExceptionMessage(errorCode.value(), errorCode.msg()));
    }

    /**
     * 业务错误的HTTP响应
     *
     * @param errorMsg 异常信息
     * @return
     */
    public static Response exceptionResponse(ExceptionMessage errorMsg) {
        return assemblyResponse(ExceptionCode.BIZ_ERROR, errorMsg);
    }

    /**
     * 业务错误的HTTP响应
     *
     * @param errorCode 异常码对象
     * @param detail    错误详细信息（一般用于调试）
     * @return
     */
    public static Response exceptionResponse(ExceptionCode errorCode, String detail) {
        return assemblyResponse(ExceptionCode.BIZ_ERROR, new ExceptionMessage(errorCode.value(), errorCode.msg(), detail));
    }

    /**
     * 生成Response对象
     *
     * @param errorCode http状态码
     * @param entity    body实体
     * @return response对象
     */
    private static Response assemblyResponse(ExceptionCode errorCode, Object entity) {
        return Response
                .status(errorCode.value())
				.header("Access-Control-Allow-Origin","*")
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
