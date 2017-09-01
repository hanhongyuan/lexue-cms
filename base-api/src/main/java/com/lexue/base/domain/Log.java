package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

/**
 * Created by lilong on 17-7-25.
 */
@Data
@MyTable("sys_log")
public class Log extends DataEntity{
    @MyColumn
    private String description;
    @MyColumn
    private String method;
    @MyColumn
    private String type;
    @MyColumn
    private String url;
    @MyColumn
    private String operName;
    @MyColumn
    private String requestIp;
    @MyColumn
    private String params;

    private String operTime;
}
