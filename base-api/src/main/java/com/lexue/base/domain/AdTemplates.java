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
@MyTable("tb_adtpl")
public class AdTemplates {


    @MyId("id")
    private String id;
    @MyColumn("tplid")
    private int templateId;
    @MyColumn("title")
    private String title;
    @MyColumn("label")
    private String label;
    @MyColumn("fcap")
    private byte frameCapacity;
    @MyColumn("fptp")
    private byte framePickType;
    @MyColumn("fst")
    private byte frameSwitchTime;
    @MyColumn("note")
    private String note;
    @MyColumn("uptime")
    private long updateTime;
    @MyColumn("upid")
    private int upid;
    @MyColumn("client")
    private String client;
    private List<AdFrame> adFrames = new ArrayList();
    @MyColumn
    private String upName;
    private String updateDate;
    private String adBoxName;
    private Integer fcaps;
    private String conditionMap;
    private int defaultFilter;

    public AdTemplates(String client) {
        this.client = client;
    }
    public AdTemplates() {
    }

}