package com.exhibition.login.action;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.opensymphony.xwork2.ActionContext;
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
	public void adminLogin() {
		String result = "";
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
		System.out.println(adminInfo);
		if (adminSession != null) {
			/**
			 * 判断密码是否匹配
			 */
			if (adminInfo.getPassword().equals(adminSession.getPassword())) {
				session.setAttribute("admin_session", adminSession);
				result="success";
			}else{
				result="error";
			}
		} else {
			result="error";
		}
		try {
			response.getWriter().write(gson.toJson(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//注销用户
	public void logout() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		ActionContext.getContext().getSession().remove("admin_session");
		System.out.println("执行退出=------");
		pw.write("logoutSuccess");
		
	}

}
