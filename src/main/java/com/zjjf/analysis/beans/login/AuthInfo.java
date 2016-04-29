package com.zjjf.analysis.beans.login;

public class AuthInfo {
	
	private Integer roleId;
	
	private Integer baseRoleUserId;
	
	private Integer baseRoleId;
	
	private Integer level;
	
	private String userId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getBaseRoleUserId() {
		return baseRoleUserId;
	}

	public void setBaseRoleUserId(Integer baseRoleUserId) {
		this.baseRoleUserId = baseRoleUserId;
	}

	public Integer getBaseRoleId() {
		return baseRoleId;
	}

	public void setBaseRoleId(Integer baseRoleId) {
		this.baseRoleId = baseRoleId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}