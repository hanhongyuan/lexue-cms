package com.lexue.sso.service.mapper;

import com.lexue.base.domain.AdTemplates;
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
public interface AdTemplatesMapper extends BaseMapper<AdTemplates> {

    @Select("select a.`title`,a.`fcap` frameCapacity,a.`fptp` framePickType,a.`fst` frameSwitchTime,a.`note`,a.`tplid` templateId,a.`uptime` updateTime,a.id  from tb_adtpl a where a.client='${client}' order by uptime desc limit ${pageIndex},${pageSize}")
    public List<AdTemplates> findPage(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize,@Param("client")String client);
    @Select("select count(*) from tb_adtpl where client='${client}'")
    public Integer findPageCount(@Param("client")String client);
    @Delete("delete from tb_adtpl where id='${id}'")
    public void deleteAdTpl(@Param("id") String id);
    @Select("select a.`title`,a.`fcap` frameCapacity,a.`fptp` framePickType,a.`fst` frameSwitchTime,a.`note`,a.`tplid` templateId,a.`uptime` updateTime,a.id from tb_adtpl a where a.tplid=${id}")
    public AdTemplates getAdTpl(@Param("id") String id);
    @Select("select a.`title`,a.`fcap` frameCapacity,a.`fptp` framePickType,a.`fst` frameSwitchTime,a.`note`,a.`tplid` templateId,a.`uptime` updateTime,a.id from tb_adtpl a where a.id='${id}'")
    public AdTemplates getAdTplToId(@Param("id") String id);

    @Select("select a.`title`,a.`fcap` frameCapacity,a.`fptp` framePickType,a.`fst` frameSwitchTime,a.`note`,a.`tplid` templateId,a.`uptime` updateTime,a.id  from tb_adtpl a where a.client='${client}'")
    public List<AdTemplates> getAll(@Param("client")String client);
}