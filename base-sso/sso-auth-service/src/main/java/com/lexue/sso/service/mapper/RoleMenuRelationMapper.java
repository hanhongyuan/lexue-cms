package com.lexue.sso.service.mapper;

import com.lexue.base.mybatis.BaseMapper;
import com.lexue.base.relation.RoleMenuRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色和菜单的关联关系
 * 
 */
@Mapper
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {

    @Select("select * from sys_role_menu where role_id='${id}'")
    List<RoleMenuRelation> getMenuIdsToRoleId(@Param("id") String id);
}
