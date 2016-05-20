package com.zjjf.analysis.controller.orders;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.services.orders.SupportOrdersService;
import com.zjjf.analysis.utils.MapUtils;

@Controller
@RequestMapping(value = "/api/sp_order")
public class SpOrderController extends BaseController {

	@Autowired
	private SupportOrdersService supportOrdersService;

	private static Logger logger = LoggerFactory.getLogger(SpOrderController.class);

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(@RequestParam(value = "menuId", required = false, defaultValue = "0") Integer menuId, Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/sp-order/spOrderList";
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(
			@RequestParam(value = "menuId", required = false, defaultValue = "3") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		logger.info("传入参数paramMap:" + paramMap);
		resultMap.put("optionList", supportOrdersService.getOptionList(baseRoleId, baseRoleUserId, level));
		resultMap.put("level", level);
		return resultMap;
	}
	
	@RequestMapping(value = "/spOrderList.do")
	@ResponseBody
	public HashMap<String, Object> querySpOrders(
			@RequestParam(value = "menuId", required = false, defaultValue = "3") Integer menuId,
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
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", supportOrdersService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = supportOrdersService.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		logger.info("传入参数paramMap:" + paramMap);
		
		resultMap.put("key_cn", supportOrdersService.getOrderTitleCn(authorityArray));
		
		long time3 = System.currentTimeMillis();
		resultMap.put("dataList", supportOrdersService.getOrderData(paramMap, authorityArray));
		System.out.println("获取列表数据=================; spent：" + (System.currentTimeMillis() - time3));
		
		resultMap.put("parentTitle", supportOrdersService.getParentTitle(menuId, baseRoleId));
		
		long time4 = System.currentTimeMillis();
		resultMap.put("totalCount", supportOrdersService.getTotalCount(paramMap));
		System.out.println("获取列表数据count=================; spent：" + (System.currentTimeMillis() - time4));
		return resultMap;
	}

	@RequestMapping(value = "/portExcel.do", method = RequestMethod.GET)
	public String portExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "3") Integer menuId) throws IOException {

		OutputStream outputStream = response.getOutputStream();

		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		
		HashMap<String, Object> paramMap = getQueryMap(request, Arrays.asList("cityId", "areaId", "spGroupId", "supportmetho", "supportStatus",
				"status", "orderNos", "storeName", "supplierName", "addTimeBegin", "addTimeEnd"));

		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", supportOrdersService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = supportOrdersService.getAuthorityFilter(authInfo.getUserId(), menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		logger.info("传入参数paramMap:" + paramMap);

		
		InputStream inputStream = supportOrdersService.exportOrderList(request, response, "订单明细报表", paramMap, authorityArray);

		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=sp_order.xls");
		response.setCharacterEncoding("utf-8");

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.close();
		return null;
	}
}
