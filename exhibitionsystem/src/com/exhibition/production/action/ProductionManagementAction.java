package com.exhibition.production.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.production.DTO.PicTypeInfoDTO;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionInfoDTO;
import com.exhibition.production.DTO.ProductionThreeFormDTO;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.service.ProductionManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
	 * 
	 * @return
	 */
	private File file;
	private String fileFileName; // 文件名
	private String fileContentType; // 文件类型
	/**
	 * 作品信息
	 */
	private production_info productionInfo;

	private String idList;

	// 定义按单个图集对象
	private production_pictures production_picture;
	
	private String pictrueMap;

	public String getPictrueMap() {
		return pictrueMap;
	}

	public void setPictrueMap(String pictrueMap) {
		this.pictrueMap = pictrueMap;
	}

	public production_pictures getProduction_picture() {
		return production_picture;
	}

	public void setProduction_picture(production_pictures production_picture) {
		this.production_picture = production_picture;
	}

	public ProductionManagementService getProductionManagementService() {
		return productionManagementService;
	}

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

	public production_info getProductionInfo() {
		return productionInfo;
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
		productionVO = productionManagementService.showPicturesVO(showAll, productionVO);
		try {
			response.getWriter().write(gson.toJson(productionVO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 上传图片的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	// 添加图片
	public void addPhoto() {

	}

	// 图片转为二进制流输出
	public String IoReadImage() throws IOException {
		System.out.println("====ppp");
		fileFileName = new String(fileFileName.getBytes("ISO8859-1"), "UTF-8");//解决图片中文路径乱码
		String linkurl = "D:\\Aupload\\test\\" + fileFileName;
		FileInputStream in = new FileInputStream(linkurl);
		ServletOutputStream out = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/png");
		try {
			out = response.getOutputStream();
			// 读取文件流
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
			in.close();
		}
		return null;
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

	/**
	 * 查询单个作品
	 */
	public void querryOneProduction() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ProductionThreeFormDTO productionThreeFormDTO = productionManagementService.querryOneProduction(productionInfo);
		try {
			response.getWriter().write(gson.toJson(productionThreeFormDTO));
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
		try {
			String res = uploadFiles.excuteUpload(file, fileFileName, fileContentType);
			response.getWriter().write(gson.toJson(
					productionManagementService.addProduction(productionInfo, production_pictures) + "," + res));
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

	// 上传文件，及保存文件信息到数据库
	public void uploadAndSavePic() {
		String code = "";
		String result = "";
		String res = "";
		try {
			String folderpath = "D:\\\\Aupload\\\\test\\\\";
			if (file != null) {
				if (file.length() <= 50 * 1024 * 1024) {
					String scrol_id = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式
					String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
					File uploadFile = new File(path);
					if (!uploadFile.exists() && !uploadFile.isDirectory()) {
						System.out.println("文件夹路径不存在，创建路径:" + folderpath);
						uploadFile.mkdirs();
					} else {
						System.out.println("文件夹路径存在:" + uploadFile);
					}
					String filename = path + File.separator + fileFileName;
					fileFileName = scrol_id + "_" + fileFileName;
					FileInputStream in = new FileInputStream(file);
					FileOutputStream out = new FileOutputStream(filename);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = in.read(b)) > 0) {
						out.write(b, 0, len);
					}
					out.close();
					System.out.println("filename==" + filename);
					File folder = new File(folderpath);
					if (!folder.exists() && !folder.isDirectory()) {
						System.out.println("文件夹路径不存在，创建路径:" + folderpath);
						folder.mkdirs();
					} else {
						System.out.println("文件夹路径存在:" + folderpath);
					}
					FileUtils.copyFile(file, new File(folderpath, fileFileName));
					code = "0";
					result = "uploadsuccess";
				}
			} else {
				result = "uploaderror";
				code = "1";
				System.out.println("上传文件发生错误");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String belongId = "";
		System.out.println("给我输出这个对象" + production_picture);
		System.out.println("输出下：" + idList);
		System.out.println("belongId??:" + (production_picture.getProduction_pictures_belong().trim().length() <= 0));
		/*
		 * //判断belongid是否为空
		 * if(production_picture.getProduction_pictures_belong()==null||
		 * production_picture.getProduction_pictures_belong()==""||production_picture.
		 * getProduction_pictures_belong().trim().length()<=0) { belongId =
		 * java.util.UUID.randomUUID().toString();
		 * production_picture.setProduction_pictures_belong(belongId);
		 * System.out.println("有没有重置belongId"); }else { belongId =
		 * production_picture.getProduction_pictures_belong(); }
		 */
		production_picture.setProduction_pictures_name(fileFileName);
		System.out.println(production_picture);
		productionManagementService.addPictrues(production_picture);
		res = "{\"code\":\" " + code + " \",\"msg\":\"" + result + "\",\"belongId\":\" " + belongId + " \"}";
		// 返回前端信息
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(gson.toJson(res));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CcdmwcC:" + production_picture);

	}

	//添加
	public void addAndComplete() {
		JSONArray json=JSONArray.fromObject(pictrueMap); //使用net.sf.json.JSONObject对象来解析json
		JSONObject jsonOne;
		Map<String,Object> map=null;
		List<Map<String, Object>> listMap=new ArrayList<Map<String,Object>>(); 
		for(int i=0;i<json.size();i++){
		map = new HashMap<String,Object>();
		         jsonOne = json.getJSONObject(i); 
		         map.put("key", (String) jsonOne.get("Key"));
		         map.put("value", (String) jsonOne.get("Value"));
		         //只保留不为空的 键值对
		         if( (String) jsonOne.get("Value")!=""&&!"".equals( (String) jsonOne.get("Value"))){
		         listMap.add(map); 
		         }
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		// 同时添加作品信息和补充图集信息
		String result = productionManagementService.addAndComplete(productionInfo,listMap);
		try {
			response.getWriter().write(gson.toJson(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询六条平时作业
	 */
	public void querrySixProduction() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<PicTypeInfoDTO> listPicTypeInfoDTO = productionManagementService.querrySixProduction();
		try {
			response.getWriter().write(gson.toJson(listPicTypeInfoDTO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
