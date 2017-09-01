package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Dict;
import com.lexue.base.domain.Log;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字典的mapper映射
 * <P>
 * 
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {
    @Select("select * from sys_log order by create_date desc limit ${pageIndex},${pageSize}")
    List<Log> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @Select("select count(*) from sys_log")
    public int findPageCount();
}
