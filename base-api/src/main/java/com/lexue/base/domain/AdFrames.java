package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * author lilong
 */
@Data
@MyTable("tb_adframe")
public class AdFrames {


    @MyId("id")
    private String id;
    @MyColumn("fmid")
    private Integer frameId;
    @MyColumn("tplid")
    private int tplId;
    @MyColumn
    private byte status;
    @MyColumn
    private String title;
    @MyColumn("type")
    private byte frameType;
    @MyColumn("prio")
    private int priority;
    @MyColumn("icap")
    private byte itemCapacity;
    @MyColumn("iptp")
    private byte itemPickType;
    @MyColumn("clxc")
    private int cellLayoutXCount;
    @MyColumn("clyc")
    private int cellLayoutYCount;
    @MyColumn("ist")
    private byte itemScrollTime;
    @MyColumn("tsea")
    private long enableTimestamp;
    @MyColumn("tsds")
    private long disableTimestamp;
    @MyColumn("eftm")
    private byte[] effectiveTimeScope;
    @MyColumn
    private String note;
    @MyColumn("uptime")
    private long updateTime;
    @MyColumn
    private int upid;
    private List<AdFrameRelation> relations = new ArrayList();

    @MyColumn
    private String client;
    private String adTplName;
    private String adResName;
    private String startTime;
    private String endTime;
    private int adResId;
    private int cellLayoutXStart;
    private int cellLayoutXEnd;
    private int cellLayoutYStart;
    private int cellLayoutYEnd;
    @MyColumn
    private String upName;

    public AdFrames(String client) {
        this.client = client;
    }
    public AdFrames() {
    }
}