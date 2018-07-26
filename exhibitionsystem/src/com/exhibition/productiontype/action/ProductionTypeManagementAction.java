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
	private String production_type_id;
	
	
	public String getProduction_type_id() {
		return production_type_id;
	}

	public void setProduction_type_id(String production_type_id) {
		this.production_type_id = production_type_id;
	}
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
			int GOBO=0;		//定义GOBO；判断file数组里面的对象是否为空;
			if(file.get(2)==null){
				GOBO=3;
			}
			try {
				PrintWriter pw=response.getWriter();
				String name1="";
				String name2="";
				String name3="";
				String RTMfileFileName="";
				String scrol_id = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式
				if(file!=null){
				if(GOBO==0){
					for(int i=0;i<file.size();i++){
						if(file.size()<= 50 * 1024 * 1024){
							String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
							File uploadFile = new File(path);
							if (!uploadFile.exists() && !uploadFile.isDirectory()) {
								uploadFile.mkdirs();
							} else {
								System.out.println("文件夹路径存在:" + uploadFile);
							}
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
				}else{
					for(int i=0;i<file.size()-1;i++){
						if(file.size()<= 50 * 1024 * 1024){
							String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
							File uploadFile = new File(path);
							if (!uploadFile.exists() && !uploadFile.isDirectory()) {
								uploadFile.mkdirs();
							} else {
								System.out.println("文件夹路径存在:" + uploadFile);
							}
							System.out.println("fileFileName====="+fileFileName.get(i));
							name1=scrol_id+fileFileName.get(0);	//背景图
							name2=scrol_id+fileFileName.get(1);	//logo
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
	

//修改类型和轮播
		public void editProductionTypeCarousel(){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			System.out.println("productionType.getProduction_type_id()===="+production_type_id);
			int GOBO=0;		//定义GOBO；判断file数组里面的对象是否为空;
			if(file.get(0)!=null){
				GOBO=1;
			}
			if(file.get(1)!=null){
				GOBO=2;
			}
			if(file.get(2)!=null){
				GOBO=3;
			}
			carousel carousel=productionTypeService.carousel(production_type_id);
			production_type production_type=productionTypeService.production_type(production_type_id);
			String scrol_id = production_type_id; // 采用时间+UUID的方式
			System.out.println("carousel===="+carousel);
			System.out.println("production_type===="+production_type);
			try {
				PrintWriter pw=response.getWriter();
				String name1="";
				String name2="";
				String name3="";
				String RTMfileFileName="";
				String filename = "";
				FileInputStream in =null;
				if(GOBO!=0){	//GOBO为0，则传过来的文件为空/反之不为空
				for(int i=0;i<file.size();i++){
					if(file.size()<= 50 * 1024 * 1024){
						String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
						File uploadFile = new File(path);
						if (!uploadFile.exists() && !uploadFile.isDirectory()) {
							uploadFile.mkdirs();
						} else {
							System.out.println("文件夹路径存在:" + uploadFile);
						}
						if(GOBO==1){
						name1=scrol_id+fileFileName.get(0);	//背景图	
						filename = path+File.separator+file.get(0).getName();
						RTMfileFileName=scrol_id+fileFileName.get(0);
						in = new FileInputStream(file.get(0));
						FileUtils.copyFile(file.get(0),new File("D:\\Aupload\\test\\",RTMfileFileName));
						}
						if(GOBO==2){
						name2=scrol_id+fileFileName.get(1);	//logo
						filename = path+File.separator+file.get(1).getName();
						RTMfileFileName=scrol_id+fileFileName.get(1);
						in = new FileInputStream(file.get(1));
						FileUtils.copyFile(file.get(1),new File("D:\\Aupload\\test\\",RTMfileFileName));
						}
						if(GOBO==3){
						name3=scrol_id+fileFileName.get(2);	//作品图	
						filename = path+File.separator+file.get(2).getName();
						RTMfileFileName=scrol_id+fileFileName.get(2);
						in = new FileInputStream(file.get(2));
						FileUtils.copyFile(file.get(2),new File("D:\\Aupload\\test\\",RTMfileFileName));
						}
						FileOutputStream out = new FileOutputStream(filename);
						byte[]b = new byte[1024];
						int len = 0;
						while((len=in.read(b))>0){
							out.write(b,0,len);
						}
						out.close();
						String linkurl="D:\\Aupload\\test\\"+fileFileName;
						System.out.println("上传成功,路径为"+path);
					}else{
						System.out.println("图片文件过大，无法上传！");
					}
				}	
				production_type.setProduction_type_name(production_type_name);
				production_type.setProduction_type_title(production_type_title);
				production_type.setProduction_type_discription(production_type_discription);
				production_type.setProduction_type_id(scrol_id);
				production_type.setProduction_type_modifytime(TimeUtil.getStringSecond());
				production_type.setProduction_type_isdelete(0);
				if(GOBO==2){
					production_type.setProduction_type_logo(name2);
				}
				if(GOBO==3){
					production_type.setProduction_type_picture(name3);
				}
				carousel.setCarousel_modifytime(TimeUtil.getStringSecond());
				carousel.setCarousel_isdelete(0);
				carousel.setCarousel_isshow(1);
				carousel.setCarousel_belong(scrol_id);
				if(GOBO==1){
					carousel.setCarousel_picture(name1);	
				}
				productionTypeService.updateCarousel(carousel);
				productionTypeService.updateType(production_type);
				pw.write("uploadsuccess"+","+name1+","+name2+","+name3);
				}else{
					production_type.setProduction_type_name(production_type_name);
					production_type.setProduction_type_title(production_type_title);
					production_type.setProduction_type_discription(production_type_discription);
					production_type.setProduction_type_id(scrol_id);
					production_type.setProduction_type_modifytime(TimeUtil.getStringSecond());
					production_type.setProduction_type_isdelete(0);
					productionTypeService.updateType(production_type);
					System.out.println("本次编辑未对图片进行修改操作~！！！");	
					pw.write("uploadsuccess"+","+"updataType");
				}
				System.out.println("程序执行完毕，1111111111111");	
//				pw.write(new Gson().toJson(res));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}		

