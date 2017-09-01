package com.lexue.base.util;


import java.io.Serializable;

/**
 * 处理返回值
 * @author WHW
 */
public class ResultUtil<T> {

	/**  成功 */
	public static<T> ResultData success(T t){
		ResultData<T> resultData = new ResultData<T>();
		resultData.setCode(CodeEnum.SUCCESS.getCode());
		resultData.setMessage(CodeEnum.SUCCESS.getMessage());
		resultData.setData(t);
		return resultData;
	}

	/** 成功 */
	public static<T> ResultData<T> success(String message,T object){
		ResultData<T> resultData = new ResultData<T>();
		resultData.setCode(CodeEnum.SUCCESS.getCode());
		resultData.setMessage(message);
		resultData.setData(object);
		return resultData;
	}
	/** 成功 */
	public static<T> ResultData<T> success(String message){
		ResultData<T> resultData = new ResultData<T>();
		resultData.setCode(CodeEnum.SUCCESS.getCode());
		resultData.setMessage(message);
		return resultData;
	}
	/** 成功  */
	public static<T> ResultData<Object> success(){
		return success(null);
	}
	
	/** 失败 */
	public static<T> ResultData<T> error(Integer code,String message){
		ResultData<T> resultData = new ResultData<T>();
		resultData.setCode(code);
		resultData.setMessage(message);
		return resultData;
	}
	/**  失败 */
	public static<T> ResultData error(T t){
		ResultData<T> resultData = new ResultData<T>();
		resultData.setCode(CodeEnum.ERROR.getCode());
		resultData.setMessage(CodeEnum.ERROR.getMessage());
		resultData.setData(t);
		return resultData;
	}
	/** 系统错误 */
	public static<T> ResultData<T> error(){
		return error(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
	}
	
	/** 公共错误 */
	public static<T> ResultData<T> error(CodeEnum codeEnum){
		return error(codeEnum.getCode(),codeEnum.getMessage());
	}

	/** 权限验证失败 */
	public static<T> ResultData<T> validateError(String message){
		return error(CodeEnum.PERMISSION_EX.getCode(),message);
	}

	/** 入参基本校验异常 */
	public static<T> ResultData<T> paramsError(String message){
		return error(CodeEnum.PARAMS_EX.getCode(),message);
	}
	
	/** 入参业务校验异常 */
	public static<T> ResultData<T> dataError(String message){
		return error(CodeEnum.DATA_EX.getCode(),message);
	}
	
	
}
