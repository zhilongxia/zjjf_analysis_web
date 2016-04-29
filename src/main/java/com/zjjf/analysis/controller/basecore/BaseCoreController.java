package com.zjjf.analysis.controller.basecore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.services.core.BaseCoreService;

@Controller
@RequestMapping(value = "/api/base_core")
public class BaseCoreController extends BaseController {

	@Autowired
	private BaseCoreService baseCoreService;

	@RequestMapping(value = "/getCityAreaGroupById.do")
	@ResponseBody
	public List<AnaDictionary> getCityAreaGroupById(@RequestBody HashMap<String, Object> paramMap) {

		if (paramMap != null && paramMap.containsKey("cityId")) {
			String cityId = paramMap.get("cityId") + "";
			return baseCoreService.getAreaByCityId(cityId);
		} else if (paramMap != null && paramMap.containsKey("areaId")) {
			String areaId = paramMap.get("areaId") + "";
			return baseCoreService.getGridByAreaId(Integer.valueOf(areaId));
		}
		return new ArrayList<AnaDictionary>();
	}
}
