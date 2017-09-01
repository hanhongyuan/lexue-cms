package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Menu;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.MenuFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单服务的远程调用的client
 * <P>
 * 
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = MenuFeignHystrix.class)
public interface MenuFeignClient {


	@GetMapping("/menu")
	ResultData<List<Menu>> getAllMenu();

	@PutMapping("/menu")
	ResultData updateMenu(@RequestBody(required = true) Menu menu);

	@PostMapping("/menu")
	ResultData addMenu(@RequestBody(required = true) Menu menu);

	@DeleteMapping("/menu/deleteMenu")
	ResultData deleteMenu(@RequestHeader("id") String id);

	@GetMapping("/menu/getMenu")
	ResultData<Menu> getMenu(@RequestHeader("id") String id);

	@GetMapping("/menu/getAllMenuToRoleId")
	ResultData<List<Menu>> getAllMenuToRoleId(@RequestHeader("roleId") String roleId);


}
