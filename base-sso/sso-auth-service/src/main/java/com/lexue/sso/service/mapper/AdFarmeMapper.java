package com.lexue.sso.service.mapper;

import com.lexue.base.domain.AdFrames;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.ws.rs.DELETE;
import java.util.List;

/**
 * author lilong
 */
@Mapper
public interface AdFarmeMapper extends BaseMapper<AdFrames>{

    @Select("select a.upName,a.`id`,a.`fmid` frameId,a.`tplid` tplId,a.`status`,a.`title`,a.`type` frameType,a.`prio` priority,a.`icap` itemCapacity,a.`iptp` itemPickType,a.`clxc` cellLayoutXCount,a.`clyc` cellLayoutYCount,a.`ist` itemScrollTime,a.`tsea` enableTimestamp,a.`tsds` disableTimestamp,a.`eftm` effectiveTimeScope,a.`note`,a.`uptime` updateTime FROM tb_adframe a where a.client='${client}' order by uptime desc limit ${pageIndex},${pageSize}")
    public List<AdFrames> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize,@Param("client")String client);
    @Select("select count(*) from tb_adframe where client='${client}' ")
    public Integer findPageCount(@Param("client")String client);
    @Delete("delete from tb_adframe where id='${id}'")
    void deleteAdFrame(@Param("id") String id);
    @Select("select a.upName,a.`id`,a.`fmid` frameId,a.`tplid` tplId,a.`status`,a.`title`,a.`type` frameType,a.`prio` priority,a.`icap` itemCapacity,a.`iptp` itemPickType,a.`clxc` cellLayoutXCount,a.`clyc` cellLayoutYCount,a.`ist` itemScrollTime,a.`tsea` enableTimestamp,a.`tsds` disableTimestamp,a.`eftm` effectiveTimeScope,a.`note`,a.`uptime` updateTime FROM tb_adframe a where id='${id}'")
    public AdFrames getAdFrame(@Param("id") String id);

    @Select("select a.upName,a.`id`,a.`fmid` frameId,a.`tplid` tplId,a.`status`,a.`title`,a.`type` frameType,a.`prio` priority,a.`icap` itemCapacity,a.`iptp` itemPickType,a.`clxc` cellLayoutXCount,a.`clyc` cellLayoutYCount,a.`ist` itemScrollTime,a.`tsea` enableTimestamp,a.`tsds` disableTimestamp,a.`eftm` effectiveTimeScope,a.`note`,a.`uptime` updateTime FROM tb_adframe a where a.tplid='${tplId}'")
    List<AdFrames> getAdFrameToTplId(@Param("tplId") String tplId);
    @Select("select a.upName,a.`id`,a.`fmid` frameId,a.`tplid` tplId,a.`status`,a.`title`,a.`type` frameType,a.`prio` priority,a.`icap` itemCapacity,a.`iptp` itemPickType,a.`clxc` cellLayoutXCount,a.`clyc` cellLayoutYCount,a.`ist` itemScrollTime,a.`tsea` enableTimestamp,a.`tsds` disableTimestamp,a.`eftm` effectiveTimeScope,a.`note`,a.`uptime` updateTime FROM tb_adframe a where a.fmid='${fmId}'")
    AdFrames getAdFrameToFmId(@Param("fmId") String fmId);

    @Select("select a.upName,a.`id`,a.`fmid` frameId,a.`tplid` tplId,a.`status`,a.`title`,a.`type` frameType,a.`prio` priority,a.`icap` itemCapacity,a.`iptp` itemPickType,a.`clxc` cellLayoutXCount,a.`clyc` cellLayoutYCount,a.`ist` itemScrollTime,a.`tsea` enableTimestamp,a.`tsds` disableTimestamp,a.`eftm` effectiveTimeScope,a.`note`,a.`uptime` updateTime FROM tb_adframe a where a.client='${client}' ")
    public List<AdFrames> getAll(@Param("client")String client);

}