package com.exhibition.production.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.service.ProductionManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 作品的Action层
 * 
 * @author LL
 *
 */
public class ProductionManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	/**
	 * service层注入
	 */
	private ProductionManagementService productionManagementService;
	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;
	/**
	 * 显示所有图片
	 */
	private String showAll;
	/**
	 * 模糊查询关键字
	 */
	private String search;
	
	private int page = 1;
	

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

	public String getShowAll() {
		return showAll;
	}

	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 实现request以及response结束
	 */
	/**
	 * 显示图片DTO
	 */
	public void showPicturesDTO() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<ProductionDTO> listProductionDTO = productionManagementService.showPicturesDTO(showAll);
		try {
			response.getWriter().write(gson.toJson(listProductionDTO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void showPicturesVO() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ProductionVO productionVO = new ProductionVO();
		productionVO.setSearch(search);
		productionVO.setPageIndex(page);
		productionVO = productionManagementService.showPicturesVO(showAll);
		response.getWriter().write(g);
	}

}
