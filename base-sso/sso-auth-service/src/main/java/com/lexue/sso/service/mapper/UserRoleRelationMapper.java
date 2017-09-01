package com.lexue.sso.service.mapper;

import com.lexue.base.mybatis.BaseMapper;
import com.lexue.base.relation.UserRoleRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {


    @Select("select * from sys_user_role where user_id='${userId}'")
    List<UserRoleRelation> getRelationToUserId(@Param("userId")String userId);

    @Delete("delete from sys_user_role where user_id='${userId}'")
    void deleteToUserId(@Param("userId") String userId);
}
