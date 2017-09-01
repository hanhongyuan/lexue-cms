package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */
@Data
@MyTable("wx_zu")
public class Group extends DataEntity {
    @MyColumn
    private Integer gid;
    @MyColumn
    private String groupName;
    @MyColumn
    private String h5url;
    private String groupSum;
    private String surplusNum;
    @MyColumn
    private String backgroundImage;

    private List<WxGroup> wxGroupList=new ArrayList<WxGroup>();
}
