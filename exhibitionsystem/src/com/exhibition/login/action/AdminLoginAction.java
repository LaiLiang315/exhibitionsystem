package com.exhibition.login.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.domain.adminAcount;
import com.exhibition.login.DTO.AdminSessionDTO;
import com.exhibition.login.service.AdminLoginService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 管理员登陆注册Action层
 * 
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

	private adminAcount adminInfo;

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

	public adminAcount getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(adminAcount adminInfo) {
		this.adminInfo = adminInfo;
	}

	/**
	 * 实现request以及response结束
	 */
	public String adminLogin() {
		String result = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		/**
		 * 设置session
		 */
		HttpSession session = ServletActionContext.getRequest().getSession();
		/**
		 * 获取前service层返回的对象
		 */
		adminAcount adminSession = adminLoginService.adminLogin(adminInfo);
		session.setAttribute("admin_session", adminSession);

		if (adminSession != null) {
			/**
			 * 判断密码是否匹配
			 */
			if (adminInfo.getPassword().equals(adminSession.getPassword())) {
				result="success";
				return result;

			} else {
				result="error";
				return result;
			}
		}
		try {
			response.getWriter().write(gson.toJson(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
