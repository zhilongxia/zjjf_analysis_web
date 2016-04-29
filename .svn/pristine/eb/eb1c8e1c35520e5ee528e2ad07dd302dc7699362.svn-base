package com.zjjf.analysis.controller.basecore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.services.core.BaseCoreServcie;

@Controller
@RequestMapping(value = "/api/base_core")
public class BaseCoreController extends BaseController {
	
	@Autowired
	private BaseCoreServcie baseCoreServcie;
	
	/**
	 * 获取交易订单数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAreaByCityId.do")
	@ResponseBody
	public  List<AnaDictionary> querySpOrders(HttpServletRequest request) {

		String cityId = request.getParameter("cityId");
		return baseCoreServcie.getAreaByCityId(cityId);
	}
}
