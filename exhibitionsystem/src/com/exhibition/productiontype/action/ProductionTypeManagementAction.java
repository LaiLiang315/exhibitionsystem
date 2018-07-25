package com.exhibition.productiontype.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;
import com.exhibition.productiontype.DTO.TypeCarouselDTO;
import com.exhibition.productiontype.service.ProductionTypeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 作品类型管理Action层
 * 
 * @author LL
 * @date 2018/07/18
 *
 */
public class ProductionTypeManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	/**
	 * service层注入
	 */
	private ProductionTypeService productionTypeService;
	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;
	/**
	 * 作品类型
	 */
	private production_type productionType;
	
	private TypeCarouselDTO typeCarouselDTO;
	/**
	 * 批量删除idlist
	 */
	private String idList;
	
	private carousel carousel;
	
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

	public production_type getProductionType() {
		return productionType;
	}

	public void setProductionType(production_type productionType) {
		this.productionType = productionType;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public carousel getCarousel() {
		return carousel;
	}

	public void setCarousel(carousel carousel) {
		this.carousel = carousel;
	}

	public TypeCarouselDTO getTypeCarouselDTO() {
		return typeCarouselDTO;
	}

	public void setTypeCarouselDTO(TypeCarouselDTO typeCarouselDTO) {
		this.typeCarouselDTO = typeCarouselDTO;
	}

	/**
	 * 实现request以及response结束
	 */

	/**
	 * 修改作品类型
	 */
	public void updateProductionType() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		String type = productionTypeService.updateProductionType(productionType);
		try {
			response.getWriter().write(gson.toJson(type));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 批量删除作品
 */
	public void deleteProductionType() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		String types = productionTypeService.deleteProductionType(idList);
		try {
			response.getWriter().write(gson.toJson(types));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询类型
	 */
	public void querryProductionType() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		TypeCarouselDTO typeDTO =  productionTypeService.querryProductionType(typeCarouselDTO,productionType);
		try {
			response.getWriter().write(gson.toJson(typeDTO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
