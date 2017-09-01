package com.lexue.sso.service.mapper;

import com.lexue.base.domain.AdRes;
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
public interface AdResourceMapper extends BaseMapper<AdRes> {

    @Select("select a.id,a.upid,a.note,a.ruri resourceURL,a.`rsid` AS resourceId ,a.rstp AS resourceType,a.`furi` AS forwardURL, a.stat AS status,a.title,a.text,a.furi AS forwardURL,a.`uptime` AS updateTime ,a.upName from tb_adres a where id='${id}'")
    public AdRes getAdResource(@Param("id")String id);
    @Select("select a.id,a.upid,a.note,a.ruri resourceURL,a.`rsid` AS resourceId ,a.rstp AS resourceType,a.`furi` AS forwardURL, a.stat AS status,a.title,a.text,a.furi AS forwardURL,a.`uptime` AS updateTime ,a.upName from tb_adres a where a.client='${client}'")
    public List<AdRes> getAll(@Param("client")String client);
    @Select("select a.id,a.upid,a.note,a.ruri resourceURL,a.`rsid` AS resourceId ,a.rstp AS resourceType,a.`furi` AS forwardURL, a.stat AS status,a.title,a.text,a.furi AS forwardURL,a.`uptime`AS updateTime ,a.upName  from tb_adres a where a.client='${client}' order by uptime desc limit ${pageIndex},${pageSize}")
    public List<AdRes> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize,@Param("client")String client);
    @Select("select count(*) from tb_adres where client='${client}'")
    public Integer findPageCount(@Param("client")String client);
    @Delete("delete from tb_adres where id='${id}'")
    public void deleteAdResource(@Param("id")String id);

    @Select("select a.id,a.upid,a.note,a.ruri resourceURL,a.`rsid` AS resourceId ,a.rstp AS resourceType,a.`furi` AS forwardURL, a.stat AS status,a.title,a.text,a.furi AS forwardURL,a.`uptime` AS updateTime ,a.upName from tb_adres a where a.rsid='${id}'")
    public AdRes getAdResourceToRsId(@Param("id")String id);

}