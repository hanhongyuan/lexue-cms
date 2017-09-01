package com.lexue.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * author lilong
 */
@Data
public class AdFilterTplModel implements Serializable{

    private Integer filterId;
    private Integer boxId;
    private int defaultFilter;
    private int status;
    private String conditionMap;
    private int logic;
    private int priority;
    private int tplid;
    private String title;
    private String label;
    private int frameCapacity;
    private int framePickType;
    private int frameSwitchTime;
    private String note;
    private int fcaps;

}