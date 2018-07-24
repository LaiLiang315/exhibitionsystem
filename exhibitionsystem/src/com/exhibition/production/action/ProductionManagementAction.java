package com.exhibition.production.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionInfoDTO;
import com.exhibition.production.DTO.ProductionThreeFormDTO;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.service.ProductionManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

import util.uploadFiles;

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
	private List<production_pictures> production_pictures;
	/**
	 * 上传图片
	 * @return
	 */
	private File file;				
	private String fileFileName;		//文件名
	private String fileContentType;	//文件类型
   /**
    * 作品信息
    */
   private production_info productionInfo;
   
   private String idList;
   
   private ProductionThreeFormDTO productionThreeFormDTO;
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

	public List<production_pictures> getProduction_pictures() {
		return production_pictures;
	}

	public void setProduction_pictures(List<production_pictures> production_pictures) {
		this.production_pictures = production_pictures;
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


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setProductionInfo(production_info productionInfo) {
		this.productionInfo = productionInfo;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public ProductionThreeFormDTO getProductionThreeFormDTO() {
		return productionThreeFormDTO;
	}

	public void setProductionThreeFormDTO(ProductionThreeFormDTO productionThreeFormDTO) {
		this.productionThreeFormDTO = productionThreeFormDTO;
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
	//添加图片
	public void addPhoto(){
		
	}
	
	//图片转为二进制流输出
		public String IoReadImage() throws IOException{
			System.out.println("====ppp");
			String linkurl="D:\\Aupload\\test\\"+fileFileName;
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
		 * 分页显示所有作品
		 */
		public void querryAllProduction() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			ProductionVO productionVO = new ProductionVO();
			System.out.println("search"+search);
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
		/**
		 * 查询单个作品
		 */
		public void querryOneProduction() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ProductionThreeFormDTO productionThreeFormDTO = productionManagementService.querryOneProduction(productionInfo);
		}
		
		/**
		 * 添加作品
		 */
		public void addProduction() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			try {
				String res=uploadFiles.excuteUpload(file, fileFileName, fileContentType);
				response.getWriter().write(gson.toJson(productionManagementService.addProduction(productionInfo,production_pictures)+","+res));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/**
		 * 批量删除作品
		 */
		public void deleteProduction() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			String prodctions = productionManagementService.deleteProduction(idList);
			try {
				response.getWriter().write(gson.toJson(prodctions));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 更改作品信息
		 */
		public void updateProdction() {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			String result = productionManagementService.updateProdction(productionInfo);
			try {
				response.getWriter().write(gson.toJson(result));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
