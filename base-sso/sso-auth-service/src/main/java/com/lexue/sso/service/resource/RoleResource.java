package com.lexue.sso.service.resource;

import com.lexue.base.domain.PageInfo;
import com.lexue.base.domain.Role;
import com.lexue.base.domain.User;
import com.lexue.base.util.DateUtils;
import com.lexue.base.util.ResponseUtil;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.RoleService;
import com.lexue.sso.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleResource {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@PostMapping
	public ResultData<Boolean> addRole(@RequestBody Role role) {
		return ResultUtil.success(roleService.add(role));
	}

	@PutMapping
	public ResultData<Boolean> updateRole(@RequestBody Role role) {
		return ResultUtil.success(roleService.update(role));
	}

	@GetMapping
	public ResultData<List<Role>> getAllRole() {
		return ResultUtil.success(roleService.findAllRole());
	}

	@GetMapping("/findPage")
	public ResultData<List<Role>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize) {
		List<Role> role = roleService.findPage(pageIndex,pageSize);
		for(Role role1:role){
			role1.setUpdateTime(DateUtils.format(role1.getUpdateDate()));
		}
		return ResultUtil.success(role);
	}

	@GetMapping("/getRole")
	public ResultData<Role> getRole(@RequestHeader("id") String id) {
		Role role = roleService.getById(id);
		return ResultUtil.success(role);
	}

	@DeleteMapping("/deleteRole")
	public ResultData<Boolean> deleteRole(@RequestHeader("id")  String id) {
		return ResultUtil.success(roleService.deleteById(id));
	}

	@GetMapping("/findPageCount")
	public ResultData<Integer> findPageCount(){
		return ResultUtil.success(roleService.findPageCount());
	}

	@GetMapping("/getMenuIdsToRoleId")
	public ResultData<String> getMenuIdsToRoleId(@RequestHeader("id") String id){
		return ResultUtil.success(roleService.getMenuIdsToRoleId(id));
	}
}
