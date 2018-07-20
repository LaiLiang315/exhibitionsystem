package com.exhibition.login.DTO;

import com.exhibition.domain.adminAcount;

/**
 * 管理员表在这个DTO中
 * 
 * @author LL
 *
 */
public class AdminSessionDTO {
	private adminAcount adminInfo;

	public adminAcount getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(adminAcount adminInfo) {
		this.adminInfo = adminInfo;
	}

	@Override
	public String toString() {
		return "AdminSessionDTO [adminInfo=" + adminInfo + "]";
	}
}
