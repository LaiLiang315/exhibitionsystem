package com.exhibition.domain;

/**
 * 管理员账号的实体类
 * 
 * @author LL
 * @date 2018/07/18
 *
 */
public class adminAcount {
	private String admin_id; // 管理员id
	private String username; // 管理员用户名
	private String password; // 管理员密码

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "adminAcount [admin_id=" + admin_id + ", username=" + username + ", password=" + password + "]";
	}

}
