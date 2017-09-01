package com.lexue.base.relation;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import com.lexue.base.domain.Role;
import jersey.repackaged.com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 角色和菜单的关联关系
 * 
 */
@MyTable("sys_role_menu")
public class RoleMenuRelation {

	@MyColumn("role_id")
	private String roleId;
	@MyColumn("menu_id")
	private String menuId;

	public RoleMenuRelation() {

	}

	public RoleMenuRelation(String roleId, String menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	/**
	 * @return roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            要设置的 roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            要设置的 menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public static List<RoleMenuRelation> assemblyRelation(Role role) {
		String roleId = role.getId();
		String menuIds = role.getMenuIds();
		List<RoleMenuRelation> list = Lists.newArrayList();
		if (StringUtils.isNotEmpty(role.getMenuIds()))
			for (String menuId : menuIds.split(","))
				if (StringUtils.isNotEmpty(menuId))
					list.add(new RoleMenuRelation(roleId, menuId));
		return list;
	}
}
