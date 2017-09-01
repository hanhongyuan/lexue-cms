package com.lexue.base.util;

/**
 * code枚举类
 * @author WHW
 */
public enum CodeEnum {

	ERROR(500,"系统错误"),
	SUCCESS(200,"成功"),

	//参数方面错误
	PARAMS_EX(1000,"参数异常"),
	IMPORT_EXCEL_ERROR(1001,"导入文件异常或无值"),

	//业务方面错误
	DATA_EX(2000,"入参数据逻辑校验错误"),
	LOGIN_ERROR(2001,"登陆异常"),
	PERMISSION_EX(2002,"权限验证失败"),
	SESSION_TIME_OUT(2003,"session过期"),
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
	BIZ_ERROR_USER_DISABLE(400001010,"用户被禁用请联系管理员"),
	/***** 参数相关错误 *****/
	BIZ_ERROR_PARAMETER(400000000, "参数错误"),
	BIZ_ERROR_PARAMETER_NULL(400000001, "空的请求参数"),
	BIZ_ERROR_PARAMETER_INVALID(400000002, "无效的请求参数"),

	/*****定时任务********/
	CRON_ERROR(400002001,"cron表达式有误，不能被解析！"),
	ERROR_CLASS_NOT_FOUNT(400002002,"找不到指定的类"),
	ERROR_NOT_CAUSE(400002003,"未知原因,添加任务失败"),
	/***** 公共服务 *****/
	BIZ_ERROR_CODE_ERROR(400000010,"请输入正确的验证码"),
	BIZ_ERROR_CODE_NOT_EXIST(400000011,"验证码不存在或者已经过期"),

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
	SYS_ERROR_RPC_SERVICE(500002003, "调用远程服务失败");
			;



	private Integer code;
	
	private String message;
	
	CodeEnum(Integer code, String message){
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
