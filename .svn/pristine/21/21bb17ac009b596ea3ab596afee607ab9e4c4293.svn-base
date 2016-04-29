package com.zjjf.analysis.services.authority;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.BaseAuthMenu;
import com.zjjf.analysis.mapper.analysis.BaseAuthMenuMapper;

@Service
public class BaseMenuAuthService {

	@Autowired
	private BaseAuthMenuMapper baseAuthMenuMapper;

	public Integer getAuthMenuId(Integer roleAuthId, Integer menuId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("authId", roleAuthId);
		paramMap.put("menuId", menuId);
		BaseAuthMenu baseAuthMenu = baseAuthMenuMapper.getAuthByMenuIdAndAuthId(paramMap);
		if (baseAuthMenu != null) {
			return baseAuthMenu.getId();
		}
		return 0;
	}
}
