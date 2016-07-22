package com.zjjf.analysis.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.analysis.base.CustomerService;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.orders.ISpOrdersService;

@Service
public class TestService {

	@Reference(version = "1.0.0")
	private ISpOrdersService spOrdersService;

	@Reference(version = "1.0.0")
	private IUserService userService;

	public Integer getData() {

		// return 1;
		return spOrdersService.getTotalCount(null, null, new HashMap<String, Object>());
	}

	public CustomerService getCustomerServiceByUserName(String userName) {

		return userService.getCustomerServiceByUserName("anaAdmin");
	}

}
