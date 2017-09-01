package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import lombok.Data;

/**
 * Created by admin on 2017/4/15.
 */
@Data
@MyTable("wx_qun")
public class WxGroup extends DataEntity{

    @MyColumn
    private Integer wid;
    @MyColumn
    private String name;
    @MyColumn
    private Integer groupNum;

    private Integer sort;
    @MyColumn
    private String qrCodePath;

    private String groupName;
    @MyColumn
    private String groupId;

    @MyColumn
    private Integer gid;

    private Group group;
}
