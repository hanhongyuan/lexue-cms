package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import com.lexue.push.common.type.TargetType;
import com.lexue.type.BusinessType;
import com.lexue.type.DataStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author lilong
 */
@Data
@MyTable("push_message")
public class PushMessage {

    @MyId("id")
    private String id;
    @MyColumn("rule_id")
    private long ruleId;
    @MyColumn("title")
    private String title;
    @MyColumn("business_type")
    private BusinessType businessType;
    @MyColumn("version_type")
    private String versionType;
    @MyColumn("priority")
    private int priority;
    @MyColumn("target_type")
    private TargetType targetType;
    @MyColumn("target_value")
    private String value;
    private List<String> targetValue = new ArrayList();
    @MyColumn("push_time")
    private long pushTime;
    @MyColumn("expire_time")
    private long expireTime;
    @MyColumn("store_offline")
    private boolean storeOffline;
    @MyColumn("status")
    private String status;
    @MyColumn("create_time")
    private long createTime;
    @MyColumn("user_id")
    private String userId;
    @MyColumn("transparent")
    private int transparent;  //透传类型
    @MyColumn("content")
    private String content;
    @MyColumn("transmission")
    private String transmission;
    @MyColumn("extraInfo")
    private String extraInfo;
    private String videoId;
    private int bType;
    private String sOffline;
    private String startTime;
    private String endTime;
    private String user;
    private String tag;
    private String plantforms;
    private String targetTypes;
    @MyColumn("client")
    private String client;
    @MyColumn("push_id")
    private String pushId;
    @MyColumn("push_status")
    private String pushStatus;

    private Map<String,Object> transm=new HashMap<>();
    private String bt;
    private String tp;
    private String tt;
}