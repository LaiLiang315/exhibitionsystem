package com.exhibition.login.service.impl;

import com.exhibition.domain.adminAcount;
import com.exhibition.login.DTO.AdminSessionDTO;
import com.exhibition.login.dao.AdminLoginDao;
import com.exhibition.login.service.AdminLoginService;

import util.BuildMd5;

/**
 * 用户登陆的Service层实现层
 * 
 * @author LL
 * @date 2018/07/17
 */
public class AdminLoginServiceImpl implements AdminLoginService {
	private AdminLoginDao adminLoginDao;

	public void setAdminLoginDao(AdminLoginDao adminLoginDao) {
		this.adminLoginDao = adminLoginDao;
	}

	@Override
	public adminAcount adminLogin(adminAcount adminInfo) {
		/**
		 * 
		 * 
		 */
		System.out.println("adminInfo"+adminInfo);
		BuildMd5 MD5 =new BuildMd5();
		System.out.println(adminInfo!=null);
		if(adminInfo!=null) {
			adminAcount admin = new adminAcount();
			admin = adminLoginDao.getAdminAcountById(adminInfo.getUsername());
			/**
			 * 如果查出来的管理员不为空
			 * 并且用户名和密码都匹配
			 */
			return admin;
		}else {
			return null;
		}
		
		
	}

}
