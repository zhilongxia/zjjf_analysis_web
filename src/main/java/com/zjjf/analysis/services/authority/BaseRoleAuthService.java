package com.zjjf.analysis.services.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.BaseRoleAuth;
import com.zjjf.analysis.mapper.analysis.BaseRoleAuthMapper;

@Service
public class BaseRoleAuthService {
	
	private Integer roleAuthId = 1;
	
	private static Logger logger = LoggerFactory.getLogger(BaseRoleAuthService.class);

	@Autowired
	private BaseRoleAuthMapper baseRoleAuthMapper;

	@Autowired
	private BaseMenuAuthService baseMenuAuthService;

	public void deleteByRoleId(Integer roleId) {

		baseRoleAuthMapper.deleteByRoleId(roleId);
	}
	
	public void deleteBy_roleId_pid(Integer pid, Integer roleId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pid", pid);
		paramMap.put("roleId", roleId);
		logger.info(BaseRoleAuthService.class + "deleteBy_roleId_menuId: roleId: " + roleId + ", pid: " + pid);
		baseRoleAuthMapper.deleteByRoleIdMenuId(paramMap);
	}

	public void add_base_role_auth(Integer menuId, Integer roleId) {

		BaseRoleAuth baseRoleAuth = new BaseRoleAuth();
		Integer menuAuthId = baseMenuAuthService.getAuthMenuId(roleAuthId, Integer.valueOf(menuId));
		if(menuAuthId == 0){
			logger.info("roleId: " + roleId + ", menuId: " + menuId + "的菜单权限Id不等于1");
			return;
		}
		baseRoleAuth.setMenuAuthId(menuAuthId);
		baseRoleAuth.setRoleId(roleId);
		baseRoleAuthMapper.insert(baseRoleAuth);
	}
	
	public List<Integer> getByMenuId(Integer menuId) {
		
		List<Integer> idList = new ArrayList<Integer>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		List<BaseRoleAuth> menuList = baseRoleAuthMapper.getByMenuId(paramMap);
		for (BaseRoleAuth baseRoleAuth : menuList) {
			idList.add(baseRoleAuth.getMenuAuthId());
		}
		return idList;
	}
	
	public List<Integer> getSelectMenu(Integer pid, Integer roleId, Integer level) {

		List<Integer> idList = new ArrayList<Integer>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pid", pid);
		paramMap.put("roleId", roleId);
		paramMap.put("level", level);
		List<BaseRoleAuth> menuList = baseRoleAuthMapper.getSelectMenuByRoleId(paramMap);
		for (BaseRoleAuth baseRoleAuth : menuList) {
			if(!idList.contains(baseRoleAuth.getMenuAuthId())){
				idList.add(baseRoleAuth.getMenuAuthId());
			}
		}
		return idList;
	}

}
