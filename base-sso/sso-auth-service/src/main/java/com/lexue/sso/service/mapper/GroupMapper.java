package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Group;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */
@Mapper
public interface GroupMapper  extends BaseMapper<Group> {

    @Select("select * from wx_zu order by update_date desc limit ${pageIndex},${pageSize}")
    List<Group> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @Select("select count(*) from wx_zu")
    public int findPageCount();

    @Select("select * from wx_zu where del_flag=0")
    public List<Group> findGroupList();
}
