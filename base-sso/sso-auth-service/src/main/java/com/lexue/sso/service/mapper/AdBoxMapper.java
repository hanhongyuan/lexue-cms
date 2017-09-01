package com.lexue.sso.service.mapper;

import com.lexue.base.domain.AdBoxs;
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
public interface AdBoxMapper extends BaseMapper<AdBoxs>{

    @Select("select a.id,a.`boxid`,a.`stat`,a.`title`,a.`label`,a.`vwtm` viewTime,a.`widv` viewWidth,a.`heiv` viewHeight,a.`unit`,a.`note`,a.`upName`,a.`uptime` updateTime from tb_adbox a where a.client='${client}' order by uptime desc limit ${pageIndex},${pageSize}")
    public List<AdBoxs> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("client")String client);
    @Select("select count(*) from tb_adbox where client='${client}'")
    public Integer findPageCount(@Param("client")String client);
    @Delete("delete from tb_adbox where id='${id}'")
    void deleteAdBox(@Param("id") String id);
    @Select("select a.id,a.`boxid`,a.`stat`,a.`title`,a.`label`,a.`vwtm` viewTime,a.`widv` viewWidth,a.`heiv` viewHeight,a.`unit`,a.`note`,a.`upName`,a.`uptime` updateTime  from tb_adbox a where id='${id}'")
    public AdBoxs getAdBoxs(@Param("id") String id);

    @Select("select a.id,a.`boxid`,a.`stat`,a.`title`,a.`label`,a.`vwtm` viewTime,a.`widv` viewWidth,a.`heiv` viewHeight,a.`unit`,a.`note`,a.`upName`,a.`uptime` updateTime  from tb_adbox a where a.boxid='${id}'")
    public AdBoxs getAdBoxsToBoxId(@Param("id") String id);

    @Select("select a.id,a.`boxid`,a.`stat`,a.`title`,a.`label`,a.`vwtm` viewTime,a.`widv` viewWidth,a.`heiv` viewHeight,a.`unit`,a.`note`,a.`upName`,a.`uptime` updateTime  from tb_adbox a inner join tb_adfilter f on a.boxid=f.boxid inner join tb_adframe fr on f.tplid=fr.tplid where fr.fmid='${fmId}'")
    public AdBoxs getAdBoxsToFmId(@Param("fmId") String fmId);


    @Select("select a.id,a.`boxid`,a.`stat`,a.`title`,a.`label`,a.`vwtm` viewTime,a.`widv` viewWidth,a.`heiv` viewHeight,a.`unit`,a.`note`,a.`upName`,a.`uptime` updateTime from tb_adbox a where a.client='${client}'")
    public List<AdBoxs> getAll(@Param("client")String client);
}