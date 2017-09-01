package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

/**
 * author lilong
 */
@Data
@MyTable("tb_advertise")
public class Advertisement {

    @MyId("ad_id")
    private Integer adId;
    @MyColumn("title")
    private String title;
    @MyColumn("ad_url")
    private String adUrl;
    @MyColumn("status")
    private Integer status;
    private String deliveryXY;
    @MyColumn("start_time")
    private String startTime;
    @MyColumn("end_time")
    private String endTime;
    @MyColumn("update_time")
    private String updateTime;
    @MyColumn("update_id")
    private String updateId;
    @MyColumn("adbox_id")
    private Integer adboxId;
    @MyColumn("adres_id")
    private Integer adResId;
    @MyColumn("update_name")
    private String upName;
    @MyColumn("adtpl_id")
    private String adTplId;
    @MyColumn("adframe_id")
    private String adFarmeId;
    @MyColumn("client")
    private String client;
    @MyColumn("position")
    private String position;
    @MyColumn("del_flag")
    private String delFlag;
    private AdBoxs adBox;
    private AdRes adRes;

    private String delStatus;
}