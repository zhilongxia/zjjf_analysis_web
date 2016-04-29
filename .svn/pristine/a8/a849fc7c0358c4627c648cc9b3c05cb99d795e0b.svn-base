package com.zjjf.analysis.controller.authority;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.services.authority.AuthorityModelServcie;

import net.sf.json.JSONObject;

@Controller
@RequestMapping({ "/api/authority/user" })
public class AuthorityUserController extends BaseController {

	@Autowired
	private AuthorityModelServcie authorityServcie;

	@RequestMapping(value = "/loadPage.do")
	public String loaded(HttpServletRequest request) {

		return "analysis/authority/user";
	}

	/**
	 * ”√ªß ⁄»®
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/grantUser.do")
	@ResponseBody
	public HashMap<String, Object> grantUser(HttpServletRequest request, @RequestBody JSONObject jsonObj) {

		String userId = (String) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		authorityServcie.grantUser(jsonObj, userId);
		return resultMap;
	}

}
