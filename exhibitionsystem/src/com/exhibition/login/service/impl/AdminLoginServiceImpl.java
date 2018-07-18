package com.exhibition.login.service.impl;

import com.exhibition.login.dao.AdminLoginDao;
import com.exhibition.login.service.AdminLoginService;

/**
 * 用户登陆的Service层实现层
 * @author LL
 * @date 2018/07/17
 */
public class AdminLoginServiceImpl implements AdminLoginService {
	private AdminLoginDao adminLoginDao;

	public void setAdminLoginDao(AdminLoginDao adminLoginDao) {
		this.adminLoginDao = adminLoginDao;
	}
	
 
}
