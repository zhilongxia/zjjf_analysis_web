package com.zjjf.analysis.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjjf.analysis.common.constants.SessionConfig;

public class BaseController {

	private static Logger logger = LoggerFactory.getLogger(BaseController.class);

	@SuppressWarnings("unchecked")
	public <T> T getCurrentUser(Class<T> t, HttpServletRequest request) {
		logger.debug("enter in getCurrentUser function");
		Object object = SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.user_session_key);
		if (object != null) {
			return (T) object;
		}
		return null;
	}

	/**
	 * 将request的参数转换成map
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, Object> getQueryMap(HttpServletRequest request, List<String> keyList) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = entries.next();
			name = entry.getKey();
			Object valueObj = entry.getValue();
			if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			if (keyList.contains(name)) {
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}
}
