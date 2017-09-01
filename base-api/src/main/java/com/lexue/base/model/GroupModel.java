package com.lexue.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lilong on 17-8-4.
 */
@Data
public class GroupModel implements Serializable{

    private String group;
    private String name;
    private String data;
    private String oper;

}
