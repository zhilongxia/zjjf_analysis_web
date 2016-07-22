package com.zjjf.analysis.controller.authority;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IRoleService;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.base.IDictionaryService;
import com.zjjf.analysis.producer.base.IRegionService;

@Controller
@RequestMapping({ "/api/authority/user" })
public class AuthorityUserController extends BaseController {

	private final Integer limit = 5;

	private static Logger logger = LoggerFactory.getLogger(AuthorityUserController.class);

	@Reference(version = "1.0.0")
	private IUserService userService;

	@Reference(version = "1.0.0")
	private IDictionaryService dictionaryService;
	
	@Reference(version = "1.0.0")
	private IRegionService regionService;

	@Reference(version = "1.0.0")
	private IRoleService roleService;

	@RequestMapping(value = "/loadPage.do")
	public String loaded(HttpServletRequest request) {

		return "analysis/auth/user";
	}

	@RequestMapping(value = "/getUserList.do")
	@ResponseBody
	public HashMap<String, Object> getUserList(HttpServletRequest request) {

		String currentPage = request.getParameter("currentPage") == null ? "0" : request.getParameter("currentPage");
		String itemsPerPage = request.getParameter("itemsPerPage") == null ? "5" : request.getParameter("itemsPerPage");

		HashMap<String, Object> paramMap = getQueryMap(request,
				Arrays.asList("userName", "createTimeBegin", "createTimeEnd", "roleId"));
		paramMap.put("pageNo", (Integer.valueOf(currentPage) - 1) * limit);
		paramMap.put("offset", Integer.valueOf(itemsPerPage));
		logger.info("²éÑ¯²ÎÊý paramMap:" + paramMap);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userService.getUserDataList(paramMap));
		resultMap.put("roleList", roleService.getAllRoleList());
		resultMap.put("totalCount", userService.getTotalCount());
		return resultMap;
	}

	@RequestMapping(value = "/user_edit.do")
	public String user_edit(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, Model model) {

		model.addAttribute("id", id);
		return "analysis/auth/user_edit";
	}

	@RequestMapping(value = "/getEditUserView.do")
	@ResponseBody
	public HashMap<String, Object> getEditUserView(HttpServletRequest request,
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> userInfoMap = userService.getUserInfo(id);
		resultMap.put("userInfo", userInfoMap);
		if (id > 0) {
			Integer level = userInfoMap.get("level") == null ? 1 : Integer.valueOf(userInfoMap.get("level") + "");
			// Integer roleId = Integer.valueOf("" + userInfoMap.get("roleId"));
			if (level == 1) {
				resultMap.put("listDict", dictionaryService.getDictionaryList(3, 6));
			} else if (level == 2) {
				resultMap.put("listDict", dictionaryService.getDictionaryList(3, 6));
				Integer roleId = userInfoMap.get("roleId") == null ? 1 : Integer.valueOf(userInfoMap.get("roleId") + "");
				Integer roleUserId = userInfoMap.get("id") == null ? 1 : Integer.valueOf(userInfoMap.get("id") + "");
				resultMap.put("selectCity", userService.getSelectCity(roleId, roleUserId));
			}
			// resultMap.put("listDict", userService.getAreaDataList(roleId,
			// id));
			resultMap.put("userList", userService.getLoginUserList(id));
		} else {
			resultMap.put("listDict", dictionaryService.getDictionaryList(3, 6));
			resultMap.put("userList", userService.getLoginUserList(id));
		}
		resultMap.put("userSelected", userService.getUserSelected(userInfoMap));
		resultMap.put("roleList", roleService.getAllRoleList());
		return resultMap;
	}

	@RequestMapping(value = "/saveUserInfo.do")
	@ResponseBody
	public HashMap<String, Object> saveUserInfo(
			@RequestParam(value = "roleId", required = false, defaultValue = "0") Integer roleId,
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestParam(value = "userName", required = true, defaultValue = "") String userName,
			@RequestParam(value = "areaCodes", required = true, defaultValue = "") String areaCodes) {

		String creater = (String) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userId);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userService.saveUserInfo(id, roleId, creater, userName, areaCodes);
		return resultMap;
	}

	@RequestMapping(value = "/deleteUser.do")
	@ResponseBody
	public HashMap<String, Object> deleteUser(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userService.deleteUser(id);
		resultMap.put("code", "0");
		return resultMap;
	}

	@RequestMapping(value = "/getAreaByCityId.do")
	@ResponseBody
	public HashMap<String, Object> getAreaByCityId(HttpServletRequest request,
			@RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId,
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {

		return regionService.getAreaByCityId(cityId, id);
	}

	@RequestMapping(value = "/userNameRegular.do")
	@ResponseBody
	public HashMap<String, Object> userNameRegular(HttpServletRequest request,
			@RequestParam(value = "userName", required = false, defaultValue = "0") String userName) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", userService.userNameRegular(userName));
		return resultMap;
	}
}
