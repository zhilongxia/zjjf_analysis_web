package com.zjjf.analysis.utils;

public class ResponseUtils {

	public static final boolean SUCCESS = true;

	public static final boolean FAILURE = false;

	public static ResponseVo sendMsg(boolean success) {
		return new ResponseVo(success);
	}

	public static ResponseVo sendMsg(boolean success, Object message) {
		return new ResponseVo(success, message);
	}

	public static ResponseVo sendMsg(boolean success, Object message, String url) {
		return new ResponseVo(success, message, url);
	}
}
