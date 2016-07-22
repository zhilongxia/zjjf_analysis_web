package com.zjjf.analysis.beans.vo;

import java.util.HashMap;

public class ResultEnum {

	private Integer code;

	private String msg;

	private HashMap<String, Object> param;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HashMap<String, Object> getParam() {
		return param;
	}

	public void setParam(HashMap<String, Object> param) {
		this.param = param;
	}

}
