package com.lexue.base.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-20.
 */
@Data
public class MenuTree {

    private String title;
    private String icon;
    private String href;
    private boolean spread;
    private List<MenuTree> children=new ArrayList<>();


}
