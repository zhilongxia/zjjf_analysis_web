package com.zjjf.analysis.services.core;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.services.base.BaseSpGroupService;

@Service
public class BaseCoreService extends AbstractBaseService {

	@Autowired
	private BaseSpGroupService baseSpGroupService;

	/**
	 * 获取Region字典
	 * 
	 * @return
	 */
	public List<AnaDictionary> getAreaByCityId(String cityId) {

		HashMap<String, Object> cityMap = new HashMap<String, Object>();
		cityMap.put("cityId", cityId);
		cityMap.put("regionLevel", 4);
		return super.baseRegionMapper.getAreaByCityId(cityMap);
	}

	public List<AnaDictionary> getGridByAreaId(Integer areaId) {

		return baseSpGroupService.getSpGroupCodeListByAreaId(areaId);
	}

	@Override
	public Object[] getColumn(Object[][] authorityArray, Integer key_or_name) {
		// TODO Auto-generated method stub
		return null;
	}

}
