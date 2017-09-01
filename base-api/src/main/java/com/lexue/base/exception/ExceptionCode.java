package com.lexue.base.exception;

import javax.ws.rs.core.Response;

/**
 * 异常代码
 * <P>
 */
public enum ExceptionCode {
	
	OK(Response.Status.OK.getStatusCode(), "成功"),

	GENERIC_ERROR(-1, "一般错误"),

	/***** 业务错误 *****/
	BIZ_ERROR(Response.Status.BAD_REQUEST.getStatusCode(), "业务错误"),

	/***** 参数相关错误 *****/
	BIZ_ERROR_PARAMETER(400000000, "参数错误"),
	BIZ_ERROR_PARAMETER_NULL(400000001, "空的请求参数"),
	BIZ_ERROR_PARAMETER_INVALID(400000002, "无效的请求参数"),
	
	/***** 公共服务 *****/
	BIZ_ERROR_CODE_ERROR(400000010,"请输入正确的验证码"),
	BIZ_ERROR_CODE_NOT_EXIST(400000011,"验证码不存在或者已经过期"),
	

	/***** 用户 *****/
	BIZ_ERROR_USER_UNKNOWN(400001000,"未知用户错误"),
	BIZ_ERROR_USER_ID_NULL(400001001,"用户ID为空"),
	BIZ_ERROR_USER_NAME_NULL(400001002,"用户名为空"),
	BIZ_ERROR_USER_PWD_NULL(400001003,"用户密码为空"),
	BIZ_ERROR_USER_COUNT_NOT_EXIST(400001004,"用户账号不存在"),
	BIZ_ERROR_USER_COUNT_EXIST(400001005,"用户账号存在"),
	BIZ_ERROR_USER_COUNT_NAME_NOMATHCH(400001006,"用户账号与密码不匹配"),
	BIZ_ERROR_USER_NOT_EXIST(400001007,"用户不存在"),
	BIZ_ERROR_USER_TOKEN_NULL(400001008,"用户TOKEN为空"),
	BIZ_ERROR_USER_NOT_AVAILABLE(400001009,"用户不可用"),
	
	
	/***** 系统错误: 500xxxyyy ****/
	SYS_ERROR(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "系统错误"),

	/***** 数据库相关错误 *****/
	SYS_ERROR_DB_UNKNOWN(500000000, "未知数据库错误"),
	SYS_ERROR_DB_INSERTING(500000001, "数据库 Insert 错误"),
	SYS_ERROR_DB_UPDATING(500000002, "数据库 Update 错误"),
	SYS_ERROR_DB_DELETING(500000003, "数据 Delete 错误"),

	/***** JSON相关错误 *****/
	SYS_ERROR_JSON_UNKNOWN(500001000, "Json 未知错误"),
	SYS_ERROR_JSON_MAPPING(500001001, "Json 数据映射错误"),

	/***** 远程调用相关错误 *****/
	SYS_ERROR_RPC_UNKNOWN(500002000, "远程调用未知错误"),
	SYS_ERROR_RPC_CONNECTION(500002001, "无法连接远程服务"),
	SYS_ERROR_NOT_CLIENT(500002003, "无法获取远程调用客户端"),
	;

	private final int value;
	private final String msg;

	private ExceptionCode(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public int value() {
		return this.value;
	}

	public String msg() {
		return this.msg;
	}
	
	public static ExceptionCode valueOf(int value) {
		for (ExceptionCode instance : values()) 
			if (instance.value == value) 
				return instance;
		throw new IllegalArgumentException("No matching constant for [" + value + "]");
	}
}
