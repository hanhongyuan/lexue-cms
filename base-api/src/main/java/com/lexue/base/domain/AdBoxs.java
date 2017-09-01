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
@MyTable("tb_adbox")
public class AdBoxs {

    @MyId
    private String id;
    @MyColumn
    private Integer boxId;
    @MyColumn
    private byte stat;
    @MyColumn
    private String title;
    @MyColumn
    private String label;
    @MyColumn("widv")
    private int viewWidth;
    @MyColumn("heiv")
    private int viewHeight;
    @MyColumn
    private byte unit;
    @MyColumn("vwtm")
    private int viewTime;
    @MyColumn
    private String note;
    @MyColumn("uptime")
    private long updateTime;
    @MyColumn
    private int upid;
    @MyColumn
    private String upName;
    private String updateDate;
    private List<AdFilter> filters = new ArrayList();
    @MyColumn
    private String client;
    private Integer status;
    private Integer company;

    public AdBoxs(String client) {
        this.client = client;
    }

    public AdBoxs() {
    }

}