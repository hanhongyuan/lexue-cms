package com.lexue.base.util;



import java.io.Serializable;

public class ResultData<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer code;
	
	private String message;
	
	private T data;
	
	private String sysTime;
	
	public ResultData() {
		super();
		this.sysTime = DateUtils.format();
	}

	public ResultData(Integer code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.sysTime = DateUtils.format();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
 }
