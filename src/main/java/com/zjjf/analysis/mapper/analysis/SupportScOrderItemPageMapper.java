package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

public interface SupportScOrderItemPageMapper {

	List<HashMap<String, Object>> getOrderData(HashMap<String, Object> paramMap);

	int getTotalCount(HashMap<String, Object> paramMap);
}