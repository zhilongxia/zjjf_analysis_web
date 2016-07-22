package com.zjjf.analysis.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MapUtils {

	public static void array_2_map(Object[][] authorityArray, HashMap<String, Object> authorityMap) {

		for (Object[] entity : authorityArray) {
			authorityMap.put(entity[0] + "", entity[1]);
		}
	}
	
	public static HashMap<String, Object> bean_2_map(Object obj) {

		if (obj == null) {
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
}
