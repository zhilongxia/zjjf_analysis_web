package com.zjjf.analysis.services.base;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.mapper.analysis.BaseSpGroupMapper;
import com.zjjf.analysis.services.authority.BaseUserLevelService;

@Service
public class BaseSpGroupService {

	@Autowired
	private BaseSpGroupMapper baseSpGroupMapper;

	@Autowired
	private BaseUserLevelService baseUserLevelService;

	public List<AnaDictionary> getSpGroupCodeList(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		HashMap<String, Object> spGroupMap = new HashMap<String, Object>();
		if (level == 2) {
			spGroupMap.put("areaIdList", baseUserLevelService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		} else if (level == 1) {
			spGroupMap.put("cityIdList", baseUserLevelService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		}
		return baseSpGroupMapper.getSpGroupCodeList(spGroupMap);
	}
	
	public List<AnaDictionary> getSpGroupCodeListByAreaId(Integer areaId) {

		HashMap<String, Object> spGroupMap = new HashMap<String, Object>();
		spGroupMap.put("areaId", areaId);
		return baseSpGroupMapper.getSpGroupCodeList(spGroupMap);
	}

}
