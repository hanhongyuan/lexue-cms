package com.lexue.base.domain;


import com.lexue.base.annotation.mybatis.MyColumn;

/**
 * 带有parent的实体
 * 
 */
public class ParentEntity extends DataEntity {
	private static final long serialVersionUID = 6680083980042440909L;
	@MyColumn("parent_ids")
	private String parentIds; // 所有父级菜单
	@MyColumn("parent_id")
	private String parentId; // 父级菜单
	
	private String oldparentIds;

	/**
	 * @return parentIds
	 */
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * @param parentIds
	 *            要设置的 parentIds
	 */
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * @return parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            要设置的 parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return oldparentIds
	 */
	public String getOldparentIds() {
		return oldparentIds;
	}

	/**
	 * @param oldparentIds 要设置的 oldparentIds
	 */
	public void setOldparentIds(String oldparentIds) {
		this.oldparentIds = oldparentIds;
	}
	
	
}
