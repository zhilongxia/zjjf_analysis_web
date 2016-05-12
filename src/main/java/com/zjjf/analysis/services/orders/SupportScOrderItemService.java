package com.zjjf.analysis.services.orders;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.mapper.analysis.SupportScOrderItemPageMapper;
import com.zjjf.analysis.services.AbstractBaseService;

@Service
public class SupportScOrderItemService extends AbstractBaseService {

	@Autowired
	private SupportScOrderItemPageMapper supportScOrderItemPageMapper;

	public List<Object[]> getOrderData(HashMap<String, Object> paramMap, Object[][] authorityArray) {

		long time1 = System.currentTimeMillis();
		List<HashMap<String, Object>> dataList = supportScOrderItemPageMapper.getOrderData(paramMap);
		System.out.println("获取订单数据查询sql,==============， spent: " + (System.currentTimeMillis() - time1));
		return stand_by_title(dataList, getColumn(authorityArray, 0));
	}

	public Integer getTotalCount(HashMap<String, Object> paramMap) {

		return supportScOrderItemPageMapper.getTotalCount(paramMap);
	}
}
