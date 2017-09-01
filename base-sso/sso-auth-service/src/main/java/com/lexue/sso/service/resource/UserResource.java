package com.lexue.sso.service.resource;

import com.lexue.base.domain.PageInfo;
import com.lexue.base.domain.User;
import com.lexue.base.exception.ExceptionCode;
import com.lexue.base.util.PasswordUtil;
import com.lexue.base.util.ResponseUtil;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(getClass());


	@PostMapping
	public ResultData addUser(@RequestBody User user) {
		userService.addUser(user);
		return ResultUtil.success();
	}

	@PutMapping
	public ResultData updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return ResultUtil.success();
	}

	@GetMapping
	public ResultData<List<User>> getAllUser() {
		return ResultUtil.success(userService.findAll());
	}

	@GetMapping("/getUser")
	public ResultData<User> getUser(@RequestHeader("id")String userId) {
		logger.info("user getUser id is {}"+userId);
		User user = userService.getById(userId);
		return ResultUtil.success(user);
	}

	@DeleteMapping("/deleteUser")
	public ResultData deleteUser(@RequestHeader("id")  String userId) {
		logger.info("user delete id is {}"+userId);
		userService.deleteById(userId);
		return ResultUtil.success();
	}

	@GetMapping("/getRids")
	public ResultData<List<String>> getRids(@RequestHeader("userId")String userId){
		return ResultUtil.success(userService.getRids(userId));
	}

	@GetMapping("/checkLoginName")
	public ResultData<Object> checkLoginName(@RequestHeader("loginName") String loginName,@RequestHeader("id") String id){
		boolean user=userService.checkLoginName(loginName,id);
		if(user)
			return ResultUtil.success(true);
		return ResultUtil.success(false);
	}
}
