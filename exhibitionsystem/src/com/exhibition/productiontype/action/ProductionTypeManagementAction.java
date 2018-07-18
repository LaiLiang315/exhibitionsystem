package com.exhibition.productiontype.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.productiontype.service.ProductionTypeService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 作品类型管理Action层
 * @author LL
 * @date 2018/07/18
 *
 */
public class ProductionTypeManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	/**
	 * service层注入
	 */
	private  ProductionTypeService productionTypeService;
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


	public void setProductionTypeService(ProductionTypeService productionTypeService) {
		this.productionTypeService = productionTypeService;
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
