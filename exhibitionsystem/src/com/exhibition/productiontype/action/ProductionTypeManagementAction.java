package com.exhibition.productiontype.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;
import com.exhibition.productiontype.DTO.TypeCarouselDTO;
import com.exhibition.productiontype.service.ProductionTypeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

import util.BuildUuid;
import util.TimeUtil;

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
	private String production_type_name;
	private String production_type_discription;
	private String production_type_title;
	
	private TypeCarouselDTO typeCarouselDTO;
	/**
	 * 批量删除idlist
	 */
	private String idList;
	private List<File> file;				
	private List<String> fileFileName;		//文件名
	private List<String> fileContentType;	//文件类型

	public String getProduction_type_name() {
		return production_type_name;
	}

	public void setProduction_type_name(String production_type_name) {
		this.production_type_name = production_type_name;
	}

	public String getProduction_type_discription() {
		return production_type_discription;
	}

	public void setProduction_type_discription(String production_type_discription) {
		this.production_type_discription = production_type_discription;
	}

	public String getProduction_type_title() {
		return production_type_title;
	}

	public void setProduction_type_title(String production_type_title) {
		this.production_type_title = production_type_title;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
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
	 * 添加作品类型
	 */
	
	//添加类型和轮播
		public void addProductionType(){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			try {
				PrintWriter pw=response.getWriter();
				String name1="";
				String name2="";
				String name3="";
				String RTMfileFileName="";
				String scrol_id = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式
				if(file!=null){
				for(int i=0;i<file.size();i++){
					if(file.size()<= 50 * 1024 * 1024){
						String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
						System.out.println("fileFileName====="+fileFileName.get(i));
						name1=scrol_id+fileFileName.get(0);	//背景图
						name2=scrol_id+fileFileName.get(1);	//logo
						name3=scrol_id+fileFileName.get(2);	//作品图
						String filename = path+File.separator+file.get(i).getName();
						RTMfileFileName=scrol_id+fileFileName.get(i);
						FileInputStream in = new FileInputStream(file.get(i));
						FileOutputStream out = new FileOutputStream(filename);
						byte[]b = new byte[1024];
						int len = 0;
						while((len=in.read(b))>0){
							out.write(b,0,len);
						}
						out.close();
						FileUtils.copyFile(file.get(i),new File("D:\\Aupload\\test\\",RTMfileFileName));
						String linkurl="D:\\Aupload\\test\\"+fileFileName;
						System.out.println("上传成功,路径为"+path);
					}else{
						System.out.println("上传文件发生错误");
					}
				}	
				carousel carousel=new carousel();
				production_type production_type=new production_type();
				production_type.setProduction_type_name(production_type_name);
				production_type.setProduction_type_title(production_type_title);
				production_type.setProduction_type_discription(production_type_discription);
				production_type.setProduction_type_id(scrol_id);
				production_type.setProduction_type_creationtime(TimeUtil.getStringSecond());
				production_type.setProduction_type_isdelete(0);
				production_type.setProduction_type_logo(name2);
				production_type.setProduction_type_picture(name3);
				carousel.setCarousel_id(UUID.randomUUID().toString());
				carousel.setCarousel_creationtime(TimeUtil.getStringSecond());
				carousel.setCarousel_isdelete(0);
				carousel.setCarousel_isshow(1);
				carousel.setCarousel_belong(scrol_id);
				carousel.setCarousel_picture(name1);
				productionTypeService.addProductionType1(production_type);
				productionTypeService.addCarousel1(carousel);
				pw.write("uploadsuccess"+","+name1+","+name2+","+name3);
				}else{
					System.out.println("file为空！！！");	
					pw.write("uploaderror");
				}
				System.out.println("程序执行完毕，1111111111111");	
//				pw.write(new Gson().toJson(res));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
		System.out.println("idList----==="+idList);
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
		TypeCarouselDTO typeDTO =  productionTypeService.querryProductionType(productionType);
		try {
			response.getWriter().write(gson.toJson(typeDTO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
