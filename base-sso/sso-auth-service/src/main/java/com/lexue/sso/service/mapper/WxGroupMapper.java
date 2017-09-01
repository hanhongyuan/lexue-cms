package com.lexue.sso.service.mapper;

import com.lexue.base.domain.WxGroup;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */
@Mapper
public interface WxGroupMapper extends BaseMapper<WxGroup> {

    @Select("select * from wx_qun order by update_date desc limit ${pageIndex},${pageSize}")
    List<WxGroup> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @Select("select count(*) from wx_qun")
    public int findPageCount();

    @Select("select ifnull(count(*),0) from wx_qun where groupId='${groupId}'")
    public int getWxGroupCount(@Param("groupId")String groupId);

    @Select("select ifnull(sum(groupNum),0) from wx_qun where groupId='${groupId}'")
    public int getWxGroupSum(@Param("groupId") String groupId);
}
