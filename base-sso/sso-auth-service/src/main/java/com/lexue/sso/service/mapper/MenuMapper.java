package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Menu;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单的mapper
 * <P>
 * 
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT * from (SELECT m.* from sys_menu m join sys_role_menu rm on m.id=rm.menu_id join sys_user_role ur on rm.role_id=ur.role_id WHERE ur.user_id='${userId}') m WHERE m.is_show='1' and m.parent_id='0' GROUP BY m.id")
    List<Menu> getUserMenuTree(@Param("userId") String userId);

    @Select("select * from sys_menu where parent_id='${id}' order by sort asc")
    List<Menu> findChindMenu(@Param("id") String id);

    @Select("select * from sys_menu where parent_id='${id}' and is_show='1' order by sort asc")
    List<Menu> findUserChindMenu(@Param("id") String id);

    @Select("select * from sys_menu where parent_id='0' order by sort asc ")
    List<Menu> findAllMenu();

    @Select("select m.* from sys_menu m join sys_role_menu r on m.id=r.menu_id where r.role_id='${roleId}'")
    List<Menu> getAllMenuToRoleId(@Param("roleId") String roleId);

    @Select("select m.* from sys_menu m join sys_role_menu rm on m.id=rm.menu_id join sys_role r on r.id=rm.role_id join sys_user_role ur on r.id=ur.role_id where ur.user_id='${userId}'")
    List<Menu> getUserMenuToUserId(@Param("userId") String userId);

    @Select("select * from sys_menu where parent_id='${id}' and is_show='1' and id in (select m.id from sys_menu m join sys_role_menu rm on m.id=rm.menu_id join sys_user_role ur on rm.role_id=ur.role_id where ur.user_id='${userId}') order by sort asc")
    List<Menu> findChindMenuToUserId(@Param("id") String id,@Param("userId") String userId);

}
