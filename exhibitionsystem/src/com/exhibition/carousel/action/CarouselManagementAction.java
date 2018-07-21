package com.exhibition.carousel.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.carousel.DTO.CarouselManagementDTO;
import com.exhibition.carousel.service.CarouselManagementService;
import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 轮播图管理的Action
 * 
 * @author LL
 * @date 2018/07/17
 */
public class CarouselManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	/**
	 * service层注入
	 */
	private CarouselManagementService carouselManagementService;
	/**
	 * 用域模型把carousel,production_type,List<CarouselDTO>放到struts中
	 */
	private carousel carousel;

	private production_type type;

	/**
	 * 批量删除idList
	 */
	private String idList;
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

	public void setCarouselManagementService(CarouselManagementService carouselManagementService) {
		this.carouselManagementService = carouselManagementService;
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

	public carousel getCarousel() {
		return carousel;
	}

	public void setCarousel(carousel carousel) {
		this.carousel = carousel;
	}

	public production_type getType() {
		return type;
	}

	public void setType(production_type type) {
		this.type = type;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	/**
	 * 查看轮播图
	 */
	public void querryCarousel() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<CarouselManagementDTO> listCarouselDTO = carouselManagementService.querryCarousel();
		try {
			response.getWriter().write(gson.toJson(listCarouselDTO));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deleteCarousel() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		String carousels = carouselManagementService.deleteCarousel(idList);
		try {
			response.getWriter().write(gson.toJson(carousels));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
