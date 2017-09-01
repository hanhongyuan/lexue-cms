package com.lexue.base.domain;


import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;

/**
 * 字典的实体
 * <P>
 * 
 */
@MyTable("sys_dict")
public class Dict extends DataEntity {

	private static final long serialVersionUID = -6832422875285113665L;
	@MyColumn
	private String value; // 数据值
	@MyColumn
	private String label; // 标签名
	@MyColumn
	private String type; // 类型
	@MyColumn
	private String description;// 描述
	@MyColumn
	private Integer sort; // 排序
	@MyColumn("parent_id")
	private String parentId;// 父Id
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
