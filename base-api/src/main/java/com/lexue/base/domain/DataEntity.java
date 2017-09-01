package com.lexue.base.domain;


import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyId;

import java.io.Serializable;
import java.util.Date;

/**
 * crud的数据实体
 * 
 */
@SuppressWarnings("serial")
public abstract class DataEntity implements Serializable{
	@MyId("id")
	protected String id;
	@MyColumn("remarks")
	protected String remarks; // 备注
	@MyColumn("create_by")
	protected String createBy; // 创建者
	@MyColumn("create_date")
	protected Date createDate; // 创建日期
	@MyColumn("update_by")
	protected String updateBy; // 更新者
	@MyColumn("update_date")
	protected Date updateDate; // 更新日期
	@MyColumn("del_flag")
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            要设置的 remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            要设置的 createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            要设置的 createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy
	 *            要设置的 updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            要设置的 updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag
	 *            要设置的 delFlag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
