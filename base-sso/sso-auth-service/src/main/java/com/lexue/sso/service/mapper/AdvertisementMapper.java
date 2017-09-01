package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Advertisement;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author lilong
 */
@Mapper
public interface AdvertisementMapper extends BaseMapper<Advertisement> {

    @Select("select * from tb_advertise order by uptime desc limit ${pageIndex},${pageSize}")
    public List<Advertisement> findPage(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

    @Select("select count(*) from tb_advertise")
    public Integer findPageCount();

    @Select("select * from tb_advertise where ad_id=${id}")
    public Advertisement getAdvertisement(@Param("id")String id);

    @Delete("delete from tb_advertise where ad_id=${id}")
    public void deleteAdvertisement(@Param("id")String id);
}