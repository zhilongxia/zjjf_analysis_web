package com.zjjf.analysis.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.analysis.base.CustomerService;
import com.zjjf.analysis.beans.vo.LoginVo;
import com.zjjf.analysis.beans.vo.ModelMsg;
import com.zjjf.analysis.common.MD5;
import com.zjjf.analysis.common.constants.LoginConstant;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IAuthorityShiroService;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.login.ILoginSerivce;
import com.zjjf.analysis.utils.ResponseUtils;

@Controller
@RequestMapping(value = "/analysis/authority")
public class LoginController extends BaseController {

	@Reference(version = "1.0.0")
	private ILoginSerivce iLoginSerivce;

	@Reference(version = "1.0.0")
	private IAuthorityShiroService authorityShiroService;

	@Reference(version = "1.0.0")
	private IUserService userService;

	@RequestMapping(value = "/scmsLoginPage.do")
	public String gotoLoginPage(HttpServletRequest request) throws IOException {

		return LoginConstant.login_url;
	}

	@RequestMapping(value = "/doLoginOut.do")
	public String doLoginOut(HttpServletRequest request) throws IOException {

		SecurityUtils.getSubject().logout();
		return LoginConstant.login_url;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/scmsMainPage.do")
	public String index(HttpServletRequest request, Model model) {

		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			CustomerService userInfo = (CustomerService) subject.getSession().getAttribute(SessionConfig.user_session_key);
			List<HashMap<String, Object>> menuTree = (List<HashMap<String, Object>>) subject.getSession()
					.getAttribute("menuTree");
			if (userInfo == null) {
				return LoginConstant.login_url;
			} else {
				model.addAttribute("logInVo", userInfo);
				model.addAttribute("menuTree", menuTree);
				return LoginConstant.main_url;
			}
		} else {
			return LoginConstant.login_url;
		}
	}

	@RequestMapping(value = "/userLoginIn.do")
	@ResponseBody
	public Object userLoginIn(LoginVo loginRo, HttpSession session, HttpServletRequest request, Model model) {

		if (loginRo == null || StringUtils.isEmpty(loginRo.getUserName()) || StringUtils.isEmpty(loginRo.getPassword())) {
			return ResponseUtils.sendMsg(false, "请输入用户名密码后登陆！");
		}
		CustomerService sp = getCurrentUser(CustomerService.class, request);
		if (sp != null) {
			return ResponseUtils.sendMsg(true, "用户已登陆", LoginConstant.already_login_url);
		}
		if (StringUtils.isEmpty(loginRo.getCheckCode())) {
			session.removeAttribute(SessionConfig.user_session_code);
			return ResponseUtils.sendMsg(false, "验证码不能为空");
		}
		String serverCode = (String) session.getAttribute(SessionConfig.user_session_code);
		if (serverCode == null || !serverCode.toLowerCase().equals(loginRo.getCheckCode().trim().toLowerCase())) {
			session.removeAttribute(SessionConfig.user_session_code);
			return ResponseUtils.sendMsg(false, "验证码错误");
		}
		try {
			UsernamePasswordToken logintoken = new UsernamePasswordToken(loginRo.getUserName(),
					MD5.StringToMd5(loginRo.getPassword()), true);
			try {
				SecurityUtils.getSubject().login(logintoken);
			} catch (UnknownAccountException une) {
				return ResponseUtils.sendMsg(false, "对不起您还没有注册");
			} catch (Exception e) {
				return ResponseUtils.sendMsg(false, "用户名或密码不正确");
			}
			ModelMsg msg = this.dealScMgSuccessLogin(loginRo.getUserName(), loginRo.getPassword());
			if (msg != null && msg.isSuccess()) {
				return ResponseUtils.sendMsg(true, "登陆成功", LoginConstant.already_login_url);
			} else {
				return ResponseUtils.sendMsg(false, msg.getMessage());
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "登陆异常：");
		}
	}
	

	private ModelMsg dealScMgSuccessLogin(String userName, String passWord) {

		CustomerService userInfo = userService.getCustomerServiceByUserName(userName);
		if (null == userInfo) {
			return new ModelMsg(false, "用户信息不存在！");
		}
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.userId, userInfo.getId());
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.userAuthMap, authorityShiroService.getAuthInfoMap(userInfo.getUserName()));
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.user_session_key, userInfo);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.menuTree, authorityShiroService.getMenuTree(userInfo.getUserName()));
		return new ModelMsg(true, "登陆成功");
	}

}
