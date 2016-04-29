package com.zjjf.analysis.services.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.authority.BaseRoleAuthority;
import com.zjjf.analysis.mapper.analysis.BaseRoleAuthorityMapper;

@Service
public class BaseRoleAuthorityService {

	@Autowired
	private BaseRoleAuthorityMapper baseRoleAuthorityMapper;

	public void add_base_role_authority(Integer roleId, Integer authId){
		
		BaseRoleAuthority baseRoleAuthority = new BaseRoleAuthority();
		baseRoleAuthority.setAuthId(authId);
		baseRoleAuthority.setRoleId(roleId);
		baseRoleAuthorityMapper.insert(baseRoleAuthority);
	}
}
