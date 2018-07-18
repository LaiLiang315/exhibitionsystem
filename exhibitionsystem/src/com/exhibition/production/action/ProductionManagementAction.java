package com.exhibition.production.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.production.service.ProductionManagementService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 作品的Action层
 * @author LL
 *
 */
public class ProductionManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
	/**
	 * service层注入
	 */
	private ProductionManagementService productionManagementService;
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

	
	public void setProductionManagementService(ProductionManagementService productionManagementService) {
		this.productionManagementService = productionManagementService;
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
}
