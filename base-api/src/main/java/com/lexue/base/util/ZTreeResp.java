package com.lexue.base.util;

import com.lexue.base.domain.Menu;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ZTree对应的实体类
 */
public class ZTreeResp {

    /** id */
    private String id;
    /** PID */
    private String pId;
    /** 名称 */
    private String name;
    /** 是否被选中 */
    private boolean checked;
    /** 是否被展开 */
    private boolean open;

    public ZTreeResp(String id, String pId, String name, boolean checked) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = checked;
        this.open = true;
    }

    public ZTreeResp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
