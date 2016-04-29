package com.zjjf.analysis.services.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseUserLevel;
import com.zjjf.analysis.mapper.analysis.BaseRegionMapper;
import com.zjjf.analysis.mapper.analysis.BaseUserLevelMapper;

@Service
public class BaseUserLevelService {

	@Autowired
	private BaseUserLevelMapper baseUserLevelMapper;
	
	@Autowired
	private BaseRegionMapper baseRegionMapper;

	public void deleteByRoleUserId(Integer roleUserId) {

		baseUserLevelMapper.deleteByRoleUserId(roleUserId);
	}

	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		List<Integer> idList = new ArrayList<Integer>();
		List<BaseUserLevel> levelData = getBaseUserLevel(baseRoleId, baseRoleUserId, level);
		for (BaseUserLevel bean : levelData) {
			idList.add(bean.getDataId());
		}
		return idList.size() > 0 ? idList : null;
	}

	public List<BaseUserLevel> getBaseUserLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", baseRoleId);
		paramMap.put("roleUserId", baseRoleUserId);
		paramMap.put("level", level);
		return baseUserLevelMapper.getDataByMap(paramMap);
	}
	
	public List<AnaDictionary> getBaseAreaList(HashMap<String, Object> paramMap) {

		List<BaseUserLevel> list = baseUserLevelMapper.getDataByMap(paramMap);
		Integer areaId = 0;
		if(list.size() > 0){
			areaId = list.get(0).getDataId();
		}
		paramMap.clear();
		paramMap.put("areaId", areaId);
		return baseRegionMapper.getAreaListByareaId(paramMap);
	}
	
	
	public List<BaseUserLevel> getDataByMap(HashMap<String, Object> paramMap){
		
		return baseUserLevelMapper.getDataByMap(paramMap);
	}
	
	public void deleteByParamMap(HashMap<String, Object> paramMap) {
		
		baseUserLevelMapper.deleteByParamMap(paramMap);
	}
	
	public void insert(BaseUserLevel baseUserLevel) {
		
		baseUserLevelMapper.insert(baseUserLevel);
	}
}
