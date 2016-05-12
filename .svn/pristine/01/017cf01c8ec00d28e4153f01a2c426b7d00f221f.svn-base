package com.zjjf.analysis.services.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.services.authority.AuthorityDataService;
import com.zjjf.analysis.services.authority.BaseUserLevelService;

@Service
public class AuthKeyFilterService extends AbstractBaseService {

	@Autowired
	private AuthorityDataService authorityDataService;

	@Autowired
	private BaseUserLevelService baseUserLevelService;

	public Object[][] getAuthorityFilter(String userId, Integer menuId) {

		return authorityDataService.getAuthorityFilter(userId, menuId);
	}

	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		return baseUserLevelService.getDataIdLevel(baseRoleId, baseRoleUserId, level);
	}
	
	public Object[] getOrderTitleCn(Object[][] authorityArray) {

		return getColumn(authorityArray, 1);
	}
	
	public Object[] getOrderTitleEn(Object[][] authorityArray) {

		return getColumn(authorityArray, 0);
	}
}
