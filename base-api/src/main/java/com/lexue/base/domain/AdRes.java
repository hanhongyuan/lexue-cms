package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

/**
 * author lilong
 */
@Data
@MyTable("tb_adres")
public class AdRes {

    @MyId("id")
    private String id;
    @MyColumn("rsid")
    private Integer resourceId;
    @MyColumn("rstp")
    private int resourceType;

    private int stat;
    @MyColumn("title")
    private String title;
    @MyColumn("text")
    private String text;
    @MyColumn("furi")
    private String forwardURL;
    @MyColumn("ruri")
    private String resourceURL;
    @MyColumn("note")
    private String note;
    @MyColumn("uptime")
    private long updateTime;
    @MyColumn("upid")
    private int upid;
    @MyColumn("client")
    private String client;
    @MyColumn("stat")
    private byte status;
    @MyColumn
    private String upName;
    private String updateDate;

    public AdRes(String client) {
        this.client = client;
    }
    public AdRes() {
    }

}