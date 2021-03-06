package com.zjjf.analysis.controller.basecore;

import java.io.IOException;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.base.IBaseCoreService;

@Controller
@RequestMapping(value = "/api/base_core")
public class BaseCoreController extends BaseController {

	@Reference(version = "1.0.0")
	private IBaseCoreService baseCoreService;

	@RequestMapping(value = "/getCityAreaGroupById.do")
	@ResponseBody
	public HashMap<String, Object> getCityAreaGroupById(@RequestBody HashMap<String, Object> paramMap) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer type = Integer.valueOf(paramMap.get("type") + "");
		if (type == 1) {
			String cityId = paramMap.get("cityId") + "";
			resultMap.put("areaSelect", baseCoreService.getAreaByCityId(cityId));
			HashMap<String, Object> spGroupMap = new HashMap<String, Object>();
			spGroupMap.put("cityId", cityId);
			resultMap.put("spGroupIdSelect", baseCoreService.getGridByParam(spGroupMap));
			return resultMap;
		} else if (type == 2) {
			String areaId = paramMap.get("areaId") + "";
			String cityId = paramMap.get("cityId") + "";
			HashMap<String, Object> spGroupMap = new HashMap<String, Object>();
			spGroupMap.put("areaId", areaId);
			spGroupMap.put("cityId", cityId);
			resultMap.put("spGroupIdSelect", baseCoreService.getGridByParam(spGroupMap));
			return resultMap;
		}
		return resultMap;
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(
			@RequestParam(value = "menuId", required = false, defaultValue = "3") Integer menuId,
			@RequestParam(value = "dispatch", required = false, defaultValue = "") String dispatch) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();

		resultMap.put("optionList", baseCoreService.getOptionList(baseRoleId, baseRoleUserId, level, dispatch));
		resultMap.put("level", level);
		return resultMap;
	}
}
