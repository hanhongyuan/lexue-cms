package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
/**
 * 角色是实体
 * 
 */
@MyTable("sys_role")
public class Role extends DataEntity {
	private static final long serialVersionUID = -6505598406354271488L;
	@MyColumn
	private String name; // 角色名称
	@MyColumn
	private String useable; // 是否是可用

	private String menuIds; // 菜单ID的集合

	private String updateTime;

	public Role() {
		super();
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return useable
	 */
	public String getUseable() {
		return useable;
	}

	/**
	 * @param useable
	 *            要设置的 useable
	 */
	public void setUseable(String useable) {
		this.useable = useable;
	}

	/**
	 * @return menuIds
	 */
	public String getMenuIds() {
		return menuIds;
	}

	/**
	 * @param menuIds
	 *            要设置的 menuIds
	 */
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}


	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
