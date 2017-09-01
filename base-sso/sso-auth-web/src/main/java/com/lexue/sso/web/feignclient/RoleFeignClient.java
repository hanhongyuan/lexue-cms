package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Role;
import com.lexue.base.domain.User;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.RoleFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色的远程调用feignclient
 * <P>
 * 
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = RoleFeignHystrix.class)
public interface RoleFeignClient {
	@GetMapping("/role/getRole")
	ResultData<Role> getRole(@RequestHeader("id") String id);

	@GetMapping("/role")
	ResultData<List<Role>> getAllRole();

	@GetMapping("/role/findPage")
	ResultData<List<Role>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

	@GetMapping("/role/findPageCount")
	ResultData<Integer> findPageCount();

	@PutMapping("/role")
	ResultData<Boolean> updateRole(@RequestBody(required = true) Role role);

	@DeleteMapping("/role/deleteRole")
	ResultData<Boolean> deleteRole(@RequestHeader("id") String id);

	@PostMapping("/role")
	ResultData<Boolean> addRole(@RequestBody(required = true) Role role);

	@GetMapping("/role/getMenuIdsToRoleId")
	ResultData<String> getMenuIdsToRoleId(@RequestHeader("id") String id);
}
