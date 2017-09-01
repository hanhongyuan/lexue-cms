package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.SsoFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户sso的远程调用的client
 * 
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = SsoFeignHystrix.class)
public interface SsoFeignClient {
	@GetMapping("/sso/logout")
	ResultData logout(@RequestHeader("tokenId") String tokenId);
	@GetMapping("/sso/checklogin")
	ResultData<User> checkLogin(@RequestHeader("tokenId") String tokenId);
	@RequestMapping(value = "/sso/userLogin",method = RequestMethod.GET)
	ResultData<User> userLogin(@RequestHeader("username") String username, @RequestHeader("password") String password);
	@GetMapping("/sso/menus")
	ResultData<List<Menu>> getUserMenus(@RequestHeader("userId") String userId);
	@GetMapping("/sso/getPermissionByCache")
	ResultData<List<String>> getPermissionByCache(@RequestHeader("userId") String userId);
}
