package com.lexue.base.util;

import lombok.Data;

import java.util.List;

/**
 * Created by lilong on 17-6-27.
 */
@Data
public class DataTablesResult<T> {

    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<T> data;

}
