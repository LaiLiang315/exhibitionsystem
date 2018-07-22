package com.exhibition.production.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionInfoDTO;
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
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 作品分页VO
	 */
	private ProductionVO productionVO;
	/**
	 * 上传图片
	 * @return
	 */
   private String uploadFileName;
   
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

	public ProductionVO getProductionVO() {
		return productionVO;
	}

	public void setProductionVO(ProductionVO productionVO) {
		this.productionVO = productionVO;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	/**
	 * 页面显示VO
	 */
	public void showPicturesVO() {
		System.out.println("showAll"+showAll);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ProductionVO productionVO = new ProductionVO();
		productionVO.setSearch(search);
		productionVO.setPageIndex(page);
		productionVO = productionManagementService.showPicturesVO( showAll,productionVO);
		try {
			response.getWriter().write(gson.toJson(productionVO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 上传图片的方法
	 * @return
	 * @throws IOException
	 */
	//图片转为二进制流输出
		public String IoReadImage() throws IOException{
			System.out.println("====ppp");
			String linkurl="D:\\Aupload\\test\\"+uploadFileName;
			FileInputStream in = new FileInputStream(new File(linkurl));
			ServletOutputStream out =null;
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("image/png");
			try {out = response.getOutputStream();
			//读取文件流
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = in.read(buffer)) != -1){
				out.write(buffer,0,len);
			}
			out.flush();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			out.close();
			in.close();
		}
		return null;
	}
/**
 * 获取单个作品信息和图集
 */
		public void getProductionInfo() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			List<ProductionInfoDTO> listProductionInfoDTO = productionManagementService.getProductionInfo();
			try {
				response.getWriter().write(gson.toJson(listProductionInfoDTO));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 添加作品
		 */
		public void addProduction() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			
		}
		/**
		 * 分页显示所有作品
		 */
		public void querryAllProduction() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			ProductionVO productionVO = new ProductionVO();
			productionVO.setSearch(search);
			productionVO.setPageIndex(page);
			productionVO = productionManagementService.querryAllProduction(productionVO);
			try {
				response.getWriter().write(gson.toJson(productionVO));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
