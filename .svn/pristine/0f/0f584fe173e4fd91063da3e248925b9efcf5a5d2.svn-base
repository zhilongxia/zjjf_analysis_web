package com.zjjf.analysis.services.authority;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.beans.analysis.base.BaseRoleData;
import com.zjjf.analysis.common.constants.Constants;
import com.zjjf.analysis.mapper.analysis.BaseRoleDataMapper;

@Service
public class AuthorityDataService extends BaseRoleService {
	
	@Autowired
	private BaseRoleDataMapper baseRoleDataMapper;
	
	
	@Autowired
	private BaseRoleService baseRoleService;

	/**
	 * 获取权限字段
	 * 
	 * @param paramMap
	 */
	public Object[][] getAuthorityFilter(String userId, Integer menuId) {

		List<BaseRole> roleList = baseRoleService.getByUserId(userId);
		String roleIds = "(";
		for (BaseRole baseRole : roleList) {
			roleIds += baseRole.getId() + ",";
		}
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("menuId", menuId);
		dataParam.put("roleIds", roleIds.substring(0, roleIds.length() - 1) + ")");
		List<BaseRoleData> templateList = baseRoleDataMapper.getAuthorityData(dataParam);
		if (templateList == null || templateList.size() == 0) {
			return new Object[][] {};
		}
		Object[][] authorityArray = new Object[templateList.size()][2];
		for (int i = 0; i < templateList.size(); i++) {
			BaseRoleData template = templateList.get(i);
			authorityArray[i][0] = Constants.authority_query + template.getAuthkey().trim();
			authorityArray[i][1] = template.getName().trim();
		}
		return authorityArray;
	}
}
