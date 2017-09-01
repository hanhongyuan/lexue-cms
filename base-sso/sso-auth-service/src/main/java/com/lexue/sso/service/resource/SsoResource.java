package com.lexue.sso.service.resource;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.exception.ExceptionCode;
import com.lexue.base.util.*;
import com.lexue.sso.service.service.CacheService;
import com.lexue.sso.service.service.MenuService;
import com.lexue.sso.service.service.UserService;
import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/sso")
public class SsoResource {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/userLogin",method = RequestMethod.GET)
	public ResultData<User> userLogin(
			@RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		logger.info("SsoResource.userLogin username.password  is {'username':'"+username+"','password':'"+password+"'}");
		User user = new User();
		user.setLoginName(username);
		List<User> list = userService.seleteAccuracy(user);
		if (list == null || list.size() == 0)
			return ResultUtil.error(CodeEnum.BIZ_ERROR_USER_NOT_EXIST);
		user = list.get(0);
		if ("1".equals(user.getDelFlag()))
			return ResultUtil.error(CodeEnum.BIZ_ERROR_USER_NOT_AVAILABLE);
		if("2".equals(user.getLoginFlag()))
			return ResultUtil.error(CodeEnum.BIZ_ERROR_USER_DISABLE);
		if (!PasswordUtil.validatePassword(password, user.getPassword()))
			return ResultUtil.error(CodeEnum.BIZ_ERROR_USER_COUNT_NAME_NOMATHCH);
		String tokenId = UUID.randomUUID().toString().replaceAll("-", "");
		user.setTokenId(tokenId);
		cacheService.saveToken(user);
		List<Menu> menuList=menuService.getUserMenuToUserId(user.getId());
		List<String> list1=new ArrayList<>();
		for(Menu menu:menuList){
			list1.add(menu.getPermission());
		}
		cacheService.saveMenu(list1,user);
		return ResultUtil.success(user);
	}

	@GetMapping("/checklogin")
	public ResultData<User> checkLogin(
			@RequestHeader("tokenId")  String tokenId) {
		User user = cacheService.getTokenUser(tokenId);
		return ResultUtil.success(user);
	}
	@GetMapping("/logout")
	public ResultData logout(@RequestHeader("tokenId")  String tokenId) {
		cacheService.clearToken(tokenId);
		return ResultUtil.success();
	}

	@GetMapping("/menus")
	public ResultData<List<Menu>> getUserMenus(@RequestHeader("userId") String userId) {
			List<Menu> menus=menuService.getUserMenuTree(userId);
			for(Menu menu:menus){
				List<Menu> chind=menuService.findChindMenuToUserId(menu.getId(),userId);
				menu.setChindMenu(chind);
			}
			return ResultUtil.success(menus);
	}
	@GetMapping("/getPermissionByCache")
	public ResultData<List<String>> getPermissionByCache(@RequestHeader("userId") String userId){
		return ResultUtil.success(cacheService.getMenuToUserId(userId));
	}
}
