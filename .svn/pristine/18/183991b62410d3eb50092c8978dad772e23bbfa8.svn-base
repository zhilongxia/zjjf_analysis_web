package com.zjjf.analysis.services.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.BaseRoleData;
import com.zjjf.analysis.beans.analysis.base.BaseRoleDataTemplate;
import com.zjjf.analysis.mapper.analysis.BaseRoleDataMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleDataTemplateMapper;

@Service
public class BaseRoleDataService {

	@Autowired
	private BaseRoleDataTemplateMapper baseRoleDataTemplateMapper;

	@Autowired
	private BaseRoleDataMapper baseRoleDataMapper;

	public List<HashMap<String, Object>> getAuthorityKeysByMenuId(Integer menuId) {

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("menuId", menuId);
		List<BaseRoleDataTemplate> dataList = baseRoleDataTemplateMapper.getRoleDataTemplate(dataParam);
		for (BaseRoleDataTemplate bean : dataList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", bean.getId());
			map.put("code", bean.getAuthkey().trim());
			map.put("name", bean.getName());
			resultList.add(map);
		}
		return resultList;
	}

	public List<Integer> getStatusBy_datatemplateId_menuId(Integer menuId, Integer roleId) {
		
		List<Integer> dataList = new ArrayList<Integer>();
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("menuId", menuId);
		dataParam.put("roleId", roleId);
		List<BaseRoleData> baseRoleDataList = baseRoleDataMapper.getBy_datatemplateId_menuId(dataParam);
		for (BaseRoleData bean : baseRoleDataList) {
			dataList.add(bean.getDatatemplateId());
		}
		return dataList;
	}
	
	public void addBaseRoleData(Integer menuId, Integer roleId, Integer datatemplateId, Integer dataLevel) {

		BaseRoleData baseRoleData = new BaseRoleData();
		baseRoleData.setIsChecked(1);
		baseRoleData.setLevel(dataLevel);
		baseRoleData.setMenuId(menuId);
		baseRoleData.setRoleId(roleId);
		baseRoleData.setDatatemplateId(datatemplateId);
		baseRoleDataMapper.insert(baseRoleData);
	}

	public void delete_by_menuId_roleId(Integer menuId, Integer roleId) {

		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("roleId", roleId);
		updateMap.put("menuId", menuId);
		baseRoleDataMapper.deleteByMenuIdAndRoleId(updateMap);
	}

	public void deleteByRoleId(Integer roleId) {
		
		baseRoleDataMapper.deleteByRoleId(roleId);
	}
	
	public List<Integer> getSelectKey(Integer menuId, Integer roleId) {
		
		List<Integer> idList = new ArrayList<Integer>();
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("roleId", roleId);
		dataParam.put("menuId", menuId);
		List<BaseRoleData> dataList = baseRoleDataMapper.getBy_datatemplateId_menuId(dataParam);
		for (BaseRoleData baseRoleData : dataList) {
			idList.add(baseRoleData.getDatatemplateId());
		}
		return idList;
	}

}
