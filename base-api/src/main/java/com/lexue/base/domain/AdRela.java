package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

/**
 * author lilong
 */
@Data
@MyTable("tb_adrela")
public class AdRela {

    @MyId("fmid")
    private int frameId;
    @MyColumn("rsid")
    private Integer resourceId;
    private AdResource adResource;
    @MyColumn
    private byte status;
    @MyColumn("prio")
    private int priority;
    @MyColumn("clxs")
    private int cellLayoutXStart;
    @MyColumn("clxe")
    private int cellLayoutXEnd;
    @MyColumn("clys")
    private int cellLayoutYStart;
    @MyColumn("clye")
    private int cellLayoutYEnd;
    @MyColumn("extra")
    private String extra;
    @MyColumn
    private String note;
    @MyColumn("uptime")
    private long updateTime;
    @MyColumn
    private int upid;
    @MyColumn
    private String client;
}