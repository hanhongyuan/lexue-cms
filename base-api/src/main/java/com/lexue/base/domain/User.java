package com.lexue.base.domain;

import com.lexue.base.annotation.mybatis.MyColumn;
import com.lexue.base.annotation.mybatis.MyTable;
import java.util.Date;

@MyTable("sys_user")
public class User extends DataEntity {

	private static final long serialVersionUID = -3175874324457699554L;
	private String tokenId;
	@MyColumn("login_name")
	private String loginName;// 登录名
	@MyColumn("password")
	private String password;// 密码
	@MyColumn("name")
	private String name; // 姓名
	@MyColumn("email")
	private String email; // 邮箱
	@MyColumn("phone")
	private String phone; // 电话
	@MyColumn("mobile")
	private String mobile; // 手机
	@MyColumn("login_ip")
	private String loginIp; // 最后登陆IP
	@MyColumn("login_flag")
	private String loginFlag; // 是否允许登陆

	private String roleIds;

	public User() {
	}

	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            要设置的 loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            要设置的 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp
	 *            要设置的 loginIp
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}


	/**
	 * @return loginFlag
	 */
	public String getLoginFlag() {
		return loginFlag;
	}

	/**
	 * @param loginFlag
	 *            要设置的 loginFlag
	 */
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}


	/**
	 * @return tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId
	 *            要设置的 tokenId
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
