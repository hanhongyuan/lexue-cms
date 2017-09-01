package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

/**
 * author lilong
 */
@Data
@MyTable("tb_adfilter")
public class AdFilters {

    @MyId("id")
    private String id;
    @MyColumn("fltid")
    private Integer filterId;
    @MyColumn("boxid")
    private Integer boxId;
    @MyColumn("dflt")
    private byte defaultFilter;
    @MyColumn
    private byte status;
    @MyColumn("fcdmap")
    private String conditionMap;
    @MyColumn
    private byte logic;
    private AdTemplate template;
    @MyColumn("prio")
    private int priority;
    @MyColumn("uptime")
    private long updateTime;
    @MyColumn
    private int upid;
    @MyColumn
    private int tplid;
    @MyColumn
    private String client;
}