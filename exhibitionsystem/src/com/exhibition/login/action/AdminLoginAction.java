package com.exhibition.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.login.service.AdminLoginService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 管理员登陆注册Action层
 * @author LL
 * @date 2018/07/18
 *
 */
public class AdminLoginAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	/**
	 * service层注入
	 */
	private AdminLoginService adminLoginService;
	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	

	public void setAdminLoginService(AdminLoginService adminLoginService) {
		this.adminLoginService = adminLoginService;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
/**
 * 实现request以及response结束
 */
<<<<<<< HEAD
=======
	public void login() {
		
	}
>>>>>>> origin/LL
}
