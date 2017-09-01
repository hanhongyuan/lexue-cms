package com.lexue.base.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-20.
 */
@Data
public class ResponseResult<T> {

    private boolean rel;
    private String msg;
    private int count;
    private List<T> list=new ArrayList<T>();
}
