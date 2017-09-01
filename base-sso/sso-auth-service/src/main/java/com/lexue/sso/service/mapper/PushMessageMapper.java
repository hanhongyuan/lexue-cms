package com.lexue.sso.service.mapper;

import com.lexue.base.domain.PushMessage;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author lilong
 */
@Mapper
public interface PushMessageMapper  extends BaseMapper<PushMessage> {

    @Select("select a.id,a.rule_id ruleId,a.title,a.business_type businessType,a.version_type versionType,a.priority," +
            "a.target_type targetType,a.target_value value,a.push_time pushTime,a.expire_time expireTime,a.store_offline storeOffline,a.status," +
            "a.create_time createTime,a.user_id userId,a.transparent,a.content,a.transmission,a.extraInfo,a.push_status pushStatus,a.push_id pushId from push_message a where id='${id}'")
    public PushMessage getPushMessage(@Param("id")String id);

    @Select("SELECT a.id,a.rule_id ruleId,a.title,a.business_type businessType,a.version_type versionType,a.priority," +
            "a.target_type targetType,a.target_value value,a.push_time pushTime,a.expire_time expireTime,a.store_offline storeOffline,a.status," +
            "a.create_time createTime,a.user_id userId,a.transparent,a.content,a.transmission,a.extraInfo,a.push_status pushStatus,a.push_id pushId FROM push_message a")
    public List<PushMessage> findAllList(@Param("client")String client);
    @Select("select a.id,a.rule_id ruleId,a.title,a.business_type businessType,a.version_type versionType,a.priority," +
            "a.target_type targetType,a.target_value value,a.push_time pushTime,a.expire_time expireTime,a.store_offline storeOffline,a.status," +
            "a.create_time createTime,a.user_id userId,a.transparent,a.content,a.transmission,a.extraInfo,a.push_status pushStatus,a.push_id pushId from push_message a where a.client='${client}' order by a.create_time desc limit ${pageIndex},${pageSize}")
    public List<PushMessage> findPage(@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize, @Param("client")String client);
    @Select("select count(*) from push_message where client='${client}'")
    public Integer findPageCount(@Param("client")String client);
    @Select("delete push_message where id='${id}'")
    public void deleteRuleInfo(@Param("id")String id);



}