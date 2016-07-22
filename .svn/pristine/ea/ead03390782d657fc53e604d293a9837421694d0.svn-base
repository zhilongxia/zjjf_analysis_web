package com.zjjf.analysis.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.zjjf.analysis.beans.analysis.base.CustomerService;
import com.zjjf.analysis.producer.authority.IAuthorityShiroService;
import com.zjjf.analysis.producer.authority.IUserService;

public class AuthorityUserInfoRealm extends AuthorizingRealm {

	@Autowired
	private IAuthorityShiroService authorityShiroService;
	
	@Autowired
	private IUserService userService;

	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		if (authcToken == null) {
			return null;
		} else {
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			String userName = token.getUsername();
			if (StringUtils.isEmpty(userName)) {
				return null;
			}
			CustomerService userInfo = userService.getCustomerServiceByUserName(userName);
			if (null == userInfo) {
				return null;
			}
			SimpleAuthenticationInfo sauth = new SimpleAuthenticationInfo(userInfo.getUserName(), userInfo.getPassword(),
					userInfo.getNickName());
			return sauth;
		}
	}

	// 获取授权信息
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String currentUsername = (String) super.getAvailablePrincipal(principals);
		if (!StringUtils.isEmpty(currentUsername)) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			if (null != currentUsername) {
				authorizationInfo.addRole(authorityShiroService.getRoleSet(currentUsername));
				authorizationInfo.addStringPermission(authorityShiroService.getPermissionsSet(currentUsername));
				return authorizationInfo;
			}
		}
		return null;
	}

}
