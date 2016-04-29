package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

public interface SupportOrderPageMapper {
	
	List<HashMap<String, Object>> getOrderData(HashMap<String, Object> paramMap);
	
	List<HashMap<String, Object>> getExcelData(HashMap<String, Object> paramMap);
	
	Integer getTotalCount(HashMap<String, Object> paramMap);
}