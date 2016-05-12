package com.zjjf.analysis.controller.orders;

import java.io.IOException;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.services.core.AuthKeyFilterService;
import com.zjjf.analysis.services.orders.SupportScOrderItemService;
import com.zjjf.analysis.utils.MapUtils;

@Controller
@RequestMapping(value = "/api/sc_order")
public class ScOrderController extends BaseController {

	@Autowired
	private SupportScOrderItemService supportScOrderItemService;

	@Autowired
	private AuthKeyFilterService authKeyFilterService;

	private static Logger logger = LoggerFactory.getLogger(ScOrderController.class);

	@RequestMapping(value = "/scOrderLoadPage.do")
	public String scOrderLoadPage(@RequestParam(value = "menuId", required = false, defaultValue = "0") Integer menuId,
			Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/sc-order/scOrderList";
	}

	@RequestMapping(value = "/scItemLoadPage.do")
	public String scItemLoadPage(@RequestParam(value = "menuId", required = false, defaultValue = "0") Integer menuId,
			Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/sc-order/scItemDetailList";
	}

	/**
	 * 获取联合采购订单列表
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/scOrderList.do")
	@ResponseBody
	public HashMap<String, Object> queryScOrders(
			@RequestParam(value = "menuId", required = true, defaultValue = "6") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String _pageNo = paramMap.get("pageNo") + "";
		String _offset = paramMap.get("offset") + "";
		Integer offset = _offset == null ? 5 : Integer.valueOf(_offset);
		Integer pageNo = _pageNo == null ? 1 : (Integer.valueOf(_pageNo) - 1) * offset;
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		String userName = authInfo.getUserId();
		
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authKeyFilterService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authKeyFilterService.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		logger.info("获取查询参数, paramMap:" + paramMap);
		
		resultMap.put("level", level);
		resultMap.put("key_cn", authKeyFilterService.getOrderTitleCn(authorityArray));
		resultMap.put("dataList", supportScOrderItemService.getOrderData(paramMap, authorityArray));
		resultMap.put("totalCount", supportScOrderItemService.getTotalCount(paramMap));
		return resultMap;
	}
}
