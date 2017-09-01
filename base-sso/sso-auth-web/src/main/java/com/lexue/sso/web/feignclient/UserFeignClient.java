package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.PageInfo;
import com.lexue.base.domain.User;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.UserFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户数据远程调用的client
 * 
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = UserFeignHystrix.class)
public interface UserFeignClient {
	@PostMapping("/user")
	ResultData addUser(@RequestBody(required = true) User user);

	@PutMapping("/user")
	ResultData updateUser(@RequestBody(required = true) User user);

	@GetMapping("/user")
	ResultData<List<User>> getAllUser();

	@DeleteMapping("/user/deleteUser")
	ResultData deleteUser(@RequestHeader("id") String userId);

	@GetMapping("/user/getUser")
	ResultData<User> getUser(@RequestHeader("id") String userId);

	@GetMapping("/user/getRids")
	ResultData<List<String>> getRids(@RequestHeader("userId") String userId);

	@GetMapping("/user/checkLoginName")
	ResultData checkLoginName(@RequestHeader("loginName") String loginName,@RequestHeader("id") String id);
}
