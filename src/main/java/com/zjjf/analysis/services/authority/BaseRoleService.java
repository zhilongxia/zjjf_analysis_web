package com.zjjf.analysis.services.authority;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.mapper.analysis.BaseRoleMapper;

@Service
public class BaseRoleService {

	@Autowired
	private BaseRoleMapper baseRoleMapper;

	public void add_base_role(BaseRole baseRole, String roleName, String creater, Integer level) {

		baseRole.setRoleNo(roleName);
		baseRole.setRoleName(roleName);
		baseRole.setRoleRemark(roleName);
		baseRole.setCreateTime(new Date());
		baseRole.setCreateUser(creater);
		baseRole.setStatus(1);
		baseRole.setOrdId(99);
		baseRole.setLevel(level);
		baseRole.setIsDelete("1");
		baseRoleMapper.insert(baseRole);
	}

	public void update_base_role(Integer id, String roleName, Integer level) {

		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("roleName", roleName);
		updateMap.put("level", level);
		updateMap.put("id", id);
		baseRoleMapper.updateRole(updateMap);
	}

	public List<BaseRole> getByUserId(String userId) {

		return baseRoleMapper.getRoleByUserId(userId);
	}

	public void deleteById(Integer id) {

		baseRoleMapper.deleteById(id);
	}

	public Integer getTotalCount() {

		return baseRoleMapper.getTotalCount();
	}

	public List<BaseRole> getAllRole() {

		return baseRoleMapper.getAllRole();
	}

	public BaseRole getRoleByRoleId(Integer roleId) {

		return baseRoleMapper.getRoleByRoleId(roleId);
	}
}
