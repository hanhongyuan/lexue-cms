package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Role;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色的mapper映射
 * 
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from sys_role order by update_date desc limit ${pageIndex},${pageSize}")
    List<Role> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @Select("select count(*) from sys_role")
    public int findPageCount();

    @Select("select * from sys_role where useable=1 and del_flag=0")
    public List<Role> findAllRole();
}
