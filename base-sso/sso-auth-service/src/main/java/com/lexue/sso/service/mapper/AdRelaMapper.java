package com.lexue.sso.service.mapper;

import com.lexue.base.domain.AdRela;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author lilong
 */

@Mapper
public interface AdRelaMapper extends BaseMapper<AdRela>{


    @Select("select a.id,a.`fmid` frameId,a.`rsid` resourceId,a.`status`,a.`prio` priority,a.`clxs` cellLayoutXStart,a.`clxe` cellLayoutXEnd,a.`clys` cellLayoutYStart,a.`clye` cellLayoutYEnd,a.`uptime` updateTime  from tb_adRela a where a.id='${id}'")
    AdRela getAdRela(@Param("id")String id);

    @Select("select a.id,a.`fmid` frameId,a.`rsid` resourceId,a.`status`,a.`prio` priority,a.`clxs` cellLayoutXStart,a.`clxe` cellLayoutXEnd,a.`clys` cellLayoutYStart,a.`clye` cellLayoutYEnd,a.`uptime` updateTime  from tb_adRela a where a.fmid='${id}'")
    AdRela getAdRelaToFarmeId(@Param("id")String id);

    @Select("select a.id,a.`fmid` frameId,a.`rsid` resourceId,a.`status`,a.`prio` priority,a.`clxs` cellLayoutXStart,a.`clxe` cellLayoutXEnd,a.`clys` cellLayoutYStart,a.`clye` cellLayoutYEnd,a.`uptime` updateTime  from tb_adRela a where a.rsid='${rsId}'")
    List<AdRela> getAdRelaToRsId(@Param("rsId")String rsId);
}