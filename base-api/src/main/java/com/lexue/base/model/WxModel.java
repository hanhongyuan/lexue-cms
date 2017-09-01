package com.lexue.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lilong on 17-8-3.
 */
@Data
public class WxModel implements Serializable {

    private Integer wid;
    private String grpn;
    private String iuri;
    private Integer size;
    private Integer gid;

}
