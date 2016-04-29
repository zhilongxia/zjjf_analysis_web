package com.zjjf.analysis.services.authority;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.authority.BaseRoleUser;
import com.zjjf.analysis.beans.analysis.base.CustomerService;
import com.zjjf.analysis.mapper.analysis.BaseRoleUserMapper;
import com.zjjf.analysis.mapper.origin.CustomerServiceMapper;

@Service
public class BaseRoleUserService {
	
	@Autowired
	private BaseRoleUserMapper baseRoleUserMapper;
	
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	
	public void add_base_role_user(Integer roleId, String userId){
		
		BaseRoleUser baseRoleUser = new BaseRoleUser();
		baseRoleUser.setRoleId(roleId);
		baseRoleUser.setUserId(userId);
		baseRoleUserMapper.insert(baseRoleUser);
	}
	
	public void deleteById(Integer id){
		
		baseRoleUserMapper.deleteById(id);
	}
	
	public HashMap<String, Object> getById(Integer id) {

		return baseRoleUserMapper.getById(id);
	}

	public List<HashMap<String, Object>> getUserList(HashMap<String, Object> paramMap) {

		return baseRoleUserMapper.getUserList(paramMap);
	}
	
	public List<CustomerService> getLoginUserList() {

		return customerServiceMapper.getLoginUserList();
	}

	public Integer getTotalCount() {
		
		return baseRoleUserMapper.getTotalCount();
	}
	
	public void insert(BaseRoleUser baseRoleUser, Integer roleId, String userName) {
	
		baseRoleUser.setRoleId(roleId);
		baseRoleUser.setUserId(userName);
		baseRoleUserMapper.insert(baseRoleUser);
	}
	
	public void updateUser(Integer id, Integer roleId, String userName) {
		
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", id);
		userMap.put("roleId", roleId);
		userMap.put("userName", userName);
		baseRoleUserMapper.updateUser(userMap);
	}
}
