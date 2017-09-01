package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的mapper映射
 * 
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	@Select("select u.* from sys_user u " + "join sys_user_role ur on u.id = ur.user_id "
			+ "where ur.role_id = '${roleId}'")
	List<User> getUserByRoleId(@Param("roleId") String roleId);

	@Select("select DISTINCT (m.permission) from sys_user_role ur "
			+ "join sys_role_menu rm  on ur.role_id = rm.role_id left join sys_menu m on rm.menu_id = m.id  "
			+ "where ur.user_id = '${userId}' and length(m.permission) >0 ")
	List<String> getUserAllAuthCodes(@Param("userId") String userId);

	@Select("select DISTINCT m.id from  sys_menu m JOIN sys_role_menu rm  on m.id = rm.menu_id JOIN sys_user_role ur on ur.user_id = rm.role_id WHERE ur.user_id = '${userId}' and m.is_show = '1'")
	List<String> getUserAllMenuIds(@Param("userId") String userId);

	@Select("select * from sys_user where login_name='${loginName}' and id!='${id}'")
	User checkLoginName(@Param("loginName")String loginName,@Param("id")String id);
}
