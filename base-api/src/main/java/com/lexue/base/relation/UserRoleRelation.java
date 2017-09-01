package com.lexue.base.relation;


import com.google.common.collect.Lists;
import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import com.lexue.base.domain.User;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 用户和角色的关联关系
 * 
 */
@MyTable("sys_user_role")
public class UserRoleRelation {

	@MyColumn("user_id")
	private String userId;

	@MyColumn("role_id")
	private String roleId;

	public UserRoleRelation() {

	}

	public UserRoleRelation(String userId, String roleId) {
		this.roleId = roleId;
		this.userId = userId;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            要设置的 userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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

	public static List<UserRoleRelation> assemblyRelation(User user) {
		String userId = user.getId();
		String roleIds = user.getRoleIds();
		List<UserRoleRelation> list = Lists.newArrayList();
		if (StringUtils.isNotEmpty(user.getRoleIds()))
			for (String roleId : roleIds.split(","))
				if (StringUtils.isNotEmpty(roleId))
					list.add(new UserRoleRelation(userId, roleId));
		return list;
	}
}
