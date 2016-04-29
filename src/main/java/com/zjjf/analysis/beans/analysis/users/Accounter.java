package com.zjjf.analysis.beans.analysis.users;

import java.util.Date;

public class Accounter {

	private String id;

	private String userName;

	private String nickName;

	private String realName;

	private Integer gender;

	private String password;

	private Integer intensity;

	private String mobile;

	private String idNum;

	private String idPicUrl0;

	private String idPicUrl1;

	private String email;

	private Date birthday;

	private Integer province;

	private Integer city;

	private Integer areaid;

	private Date regTime;

	private Date lastTime;

	private Date updateTime;

	private String regIP;

	private String lastIP;

	private String url;

	private String useAddress;

	private Boolean isManager;

	private Byte isModify;

	private String token;

	private Byte status;

	private Boolean isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getIntensity() {
		return intensity;
	}

	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum == null ? null : idNum.trim();
	}

	public String getIdPicUrl0() {
		return idPicUrl0;
	}

	public void setIdPicUrl0(String idPicUrl0) {
		this.idPicUrl0 = idPicUrl0 == null ? null : idPicUrl0.trim();
	}

	public String getIdPicUrl1() {
		return idPicUrl1;
	}

	public void setIdPicUrl1(String idPicUrl1) {
		this.idPicUrl1 = idPicUrl1 == null ? null : idPicUrl1.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP == null ? null : regIP.trim();
	}

	public String getLastIP() {
		return lastIP;
	}

	public void setLastIP(String lastIP) {
		this.lastIP = lastIP == null ? null : lastIP.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getUseAddress() {
		return useAddress;
	}

	public void setUseAddress(String useAddress) {
		this.useAddress = useAddress == null ? null : useAddress.trim();
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Byte getIsModify() {
		return isModify;
	}

	public void setIsModify(Byte isModify) {
		this.isModify = isModify;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}