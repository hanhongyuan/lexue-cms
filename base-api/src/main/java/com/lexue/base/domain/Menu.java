package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单的实体
 * <P>
 * 
 */
@MyTable("sys_menu")
@Data
public class Menu extends ParentEntity {
	private static final long serialVersionUID = 2922789604779743687L;

	@MyColumn
	private String name; // 名称
	@MyColumn
	private String href; // 链接
	@MyColumn
	private String target; // 目标（ mainFrame、_blank、_self、_parent、_top）
	@MyColumn
	private String icon; // 图标
	@MyColumn
	private Integer sort; // 排序
	@MyColumn("is_show")
	private String isShow; // 是否在菜单中显示（1：显示；0：不显示）
	@MyColumn("permission")
	private String permission; // 权限标识

	private String pname;
	private int level;
	List<Menu> chindMenu=new ArrayList<Menu>();
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	public List<Menu> getChindMenu() {
		return chindMenu;
	}

	public void setChindMenu(List<Menu> chindMenu) {
		this.chindMenu = chindMenu;
	}

	/**
	 * @param name
	 *            要设置的 name

	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href
	 *            要设置的 href
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            要设置的 target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            要设置的 icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            要设置的 sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return isShow
	 */
	public String getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            要设置的 isShow
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            要设置的 permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
