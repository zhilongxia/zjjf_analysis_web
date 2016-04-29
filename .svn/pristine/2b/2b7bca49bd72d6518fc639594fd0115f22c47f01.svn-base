package com.zjjf.analysis.services.orders;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseUserLevel;
import com.zjjf.analysis.mapper.analysis.SupportOrderPageMapper;
import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.services.authority.AuthorityDataService;
import com.zjjf.analysis.services.authority.BaseUserLevelService;
import com.zjjf.analysis.services.base.AnaDictionaryService;
import com.zjjf.analysis.services.base.BaseSpGroupService;

@Service
public class SupportOrdersService extends AbstractBaseService {

	@Autowired
	private SupportOrderPageMapper supportOrderPageMapper;

	@Autowired
	private AuthorityDataService authorityDataService;

	@Autowired
	private BaseUserLevelService baseUserLevelService;

	@Autowired
	private BaseSpGroupService baseSpGroupService;

	@Autowired
	private AnaDictionaryService anaDictionaryService;

	public Object[] getOrderTitleEn(Object[][] authorityArray) {

		return getColumn(authorityArray, 0);
	}

	public Object[] getOrderTitleCn(Object[][] authorityArray) {

		return getColumn(authorityArray, 1);
	}

	/**
	 * 导出文件
	 * 
	 * @param request
	 * @param response
	 */
	public InputStream exportOrderList(HttpServletRequest request, HttpServletResponse response, String sheetName,
			HashMap<String, Object> paramMap, Object[][] authorityArray) {

		List<Object[]> dataList = getExcelData(paramMap, authorityArray);
		Object[] titleColumn = getOrderTitleCn(authorityArray);
		return this.createExcel(sheetName, titleColumn, dataList);
	}

	public List<Object[]> getOrderData(HashMap<String, Object> paramMap, Object[][] authorityArray) {

		paramMap.putAll(array_2_map(authorityArray));
		List<HashMap<String, Object>> dataList = supportOrderPageMapper.getOrderData(paramMap);
		return stand_by_title(dataList, getOrderTitleEn(authorityArray));
	}

	private HashMap<String, Object> array_2_map(Object[][] authorityArray) {

		HashMap<String, Object> authorityMap = new HashMap<String, Object>();
		for (Object[] entity : authorityArray) {
			authorityMap.put(entity[0] + "", entity[1]);
		}
		return authorityMap;
	}

	public Integer getTotalCount(HashMap<String, Object> paramMap) {

		return supportOrderPageMapper.getTotalCount(paramMap);
	}

	public List<Object[]> getExcelData(HashMap<String, Object> paramMap, Object[][] authorityArray) {

		List<HashMap<String, Object>> dataList = supportOrderPageMapper.getExcelData(paramMap);

		return stand_by_title(dataList, getOrderTitleEn(authorityArray));
	}

	public List<List<AnaDictionary>> getOptionList(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		List<List<AnaDictionary>> optionList = new ArrayList<List<AnaDictionary>>();
		optionList.add(0, getDefaultUserLevelData(baseRoleId, baseRoleUserId, level));
		optionList.add(1, baseSpGroupService.getSpGroupCodeList(baseRoleId, baseRoleUserId, level));
		optionList.add(2, anaDictionaryService.getByDictId("sp_order_supportmetho"));
		optionList.add(3, anaDictionaryService.getByDictId("sp_order_supportStatus"));
		optionList.add(4, anaDictionaryService.getByDictId("sp_order_status"));
		return optionList;
	}

	public Object[][] getAuthorityFilter(String userId, Integer menuId) {

		return authorityDataService.getAuthorityFilter(userId, menuId);
	}

	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		List<BaseUserLevel> levelData = baseUserLevelService.getBaseUserLevel(baseRoleId, baseRoleUserId, level);
		List<AnaDictionary> levelViewList = new ArrayList<AnaDictionary>();
		for (BaseUserLevel baseUserLevel : levelData) {
			AnaDictionary d = new AnaDictionary();
			d.setCode(baseUserLevel.getDataId() + "");
			d.setName(baseUserLevel.getName());
			levelViewList.add(d);
		}
		return levelViewList;
	}
	
	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {
		
		return baseUserLevelService.getDataIdLevel(baseRoleId, baseRoleUserId, level);
	}
}
