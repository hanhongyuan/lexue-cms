package com.lexue.sso.service.mapper;

import com.lexue.base.domain.AdFilters;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author lilong
 */
@Mapper
public interface AdFilterMapper extends BaseMapper<AdFilters>{


    @Select("select a.id,a.`fltid` filterId,a.`boxid` boxId,a.`dflt` defaultFilter,a.`fcdmap` conditionMap,a.`logic`,a.`prio` priority,a.`status`,a.`tplid`,a.`uptime` updateTime from tb_adfilter a where a.tplid='${tplId}'")
    public AdFilters getAdFilterToTplId(@Param("tplId")String tplId);

    @Select("select a.id,a.`fltid` filterId,a.`boxid` boxId,a.`dflt` defaultFilter,a.`fcdmap` conditionMap,a.`logic`,a.`prio` priority,a.`status`,a.`tplid`,a.`uptime` updateTime from tb_adfilter a where a.fltid='${id}'")
    public AdFilters getAdFilterById(@Param("id")String id);

    @Select("select a.id,a.`fltid` filterId,a.`boxid` boxId,a.`dflt` defaultFilter,a.`fcdmap` conditionMap,a.`logic`,a.`prio` priority,a.`status`,a.`tplid`,a.`uptime` updateTime from tb_adfilter a where a.boxid='${boxId}'")
    List<AdFilters> findAllFilterToBoxId(@Param("boxId")String boxId);

}