package com.zjjf.analysis.services.authority;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.mapper.analysis.BaseMenuMapper;

@Service
public class BaseMenuService {

	@Autowired
	private BaseMenuMapper baseMenuMapper;

	@Autowired
	private BaseRoleDataService baseRoleDataService;
	
	@Autowired
	private BaseRoleAuthService baseRoleAuthService;

	public List<HashMap<String, Object>> getAllMenu(Integer id, Integer pid, Integer level) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("level", level);
		paramMap.put("id", id);
		paramMap.put("pid", pid);
		return baseMenuMapper.getAllMenu(paramMap);
	}

	public List<HashMap<String, Object>> getAllMenuTree(Integer roleId) {

		List<HashMap<String, Object>> menuTree = getAllMenu(null, null, 1);
		for (HashMap<String, Object> hashMap : menuTree) {
			Integer id = Integer.valueOf("" + hashMap.get("id"));
			hashMap.put("level2Tree", getAllMenu(null, id, 2));
			hashMap.put("keyLists", baseRoleDataService.getAuthorityKeysByMenuId(id));
			hashMap.put("keySelect", baseRoleDataService.getStatusBy_datatemplateId_menuId(id, roleId));
			hashMap.put("menuSelect", baseRoleAuthService.getByMenuId(id));
		}
		return menuTree;
	}
}
