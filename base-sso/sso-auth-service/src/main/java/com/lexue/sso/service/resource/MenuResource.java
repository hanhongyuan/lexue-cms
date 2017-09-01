package com.lexue.sso.service.resource;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.PageInfo;
import com.lexue.base.util.ResponseUtil;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuResource {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuService menuService;

	@PostMapping
	public ResultData addMenu(@RequestBody Menu menu) {
		logger.info("MenuResource.addMenu menu add");
		menuService.add(menu);
		return ResultUtil.success();
	}

	@PutMapping
	public ResultData updateMenu(@RequestBody Menu menu) {
		logger.info("MenuResource.updateMenu menu update");
		menuService.update(menu);
		return ResultUtil.success();
	}

	@GetMapping
	public ResultData<List<Menu>> getAllMenu() {
		logger.info("MenuResource.getAllMenu menu list");
		List<Menu> list=menuService.findAllMenu();
		if(list!=null&&list.size()>0){
			for(Menu menu:list){
				menu.setLevel(1);
				List<Menu> chind=menuService.findChindMenu(menu.getId());
				if(chind!=null&&chind.size()>0){
					for(Menu me:chind){
						me.setLevel(2);
						List<Menu> chind2=menuService.findChindMenu(me.getId());
						if(chind2!=null&&chind2.size()>0){
							for(Menu me2:chind2){
								me2.setLevel(3);
							}
						}
						me.setChindMenu(chind2);
					}
				}
				menu.setChindMenu(chind);
			}
		}
		return ResultUtil.success(list);
	}

	@GetMapping("/getMenu")
	public ResultData<Menu> getMenu(@RequestHeader("id") String id) {
		logger.info("MenuResource.getMenu menu id is{}"+id);
		Menu menu = menuService.getById(id);
		if(menu.getParentId().equals("0")){
			menu.setPname("主菜单");
		}else{
			menu.setPname(menuService.getById(menu.getParentId()).getName());
		}
		return ResultUtil.success(menu);
	}

	@DeleteMapping("/deleteMenu")
	public ResultData deleteMenu(@RequestHeader("id")  String id) {
		logger.info("MenuResource.deleteMenu menu id is{}"+id);
		menuService.deleteById(id);
		return ResultUtil.success();
	}

	@GetMapping("/getAllMenuToRoleId")
	public ResultData<List<Menu>> getAllMenuToRoleId(@RequestHeader("roleId") String roleId){
		List<Menu> list=menuService.getAllMenuToRoleId(roleId);
		return ResultUtil.success(list);
	}
}
