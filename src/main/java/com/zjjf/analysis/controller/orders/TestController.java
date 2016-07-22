package com.zjjf.analysis.controller.orders;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjjf.analysis.service.TestService;

@Controller
@RequestMapping(value = "/api/test")
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/test.do")
	@ResponseBody
	public HashMap<String, Object> queryScOrders() {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", testService.getData());
		resultMap.put("customer", testService.getCustomerServiceByUserName("anaAdmin"));
		return resultMap;
	}
}
