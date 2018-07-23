package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class uploadFiles {

	public static String excuteUpload(File file,String fileFileName,String fileContentType) throws Exception {

		String code="";
		String result="";
		String res="";
		String folderpath="D:\\\\Aupload\\\\test\\\\";
				if(file!=null){
					if(file.length()<= 50 * 1024 * 1024){
						String scrol_id = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式
						String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
						String filename = path+File.separator+fileFileName;
						fileFileName=scrol_id+"_"+fileFileName;
						FileInputStream in = new FileInputStream(file);
						FileOutputStream out = new FileOutputStream(filename);
						byte[]b = new byte[1024];
						int len = 0;
						while((len=in.read(b))>0){
							out.write(b,0,len);
						}
						out.close();
						System.out.println("filename=="+filename);
						File folder = new File(folderpath);
						if (!folder.exists() && !folder.isDirectory()) {
				            System.out.println("文件夹路径不存在，创建路径:" + folderpath);
				            folder.mkdirs();
				        } else {
				            System.out.println("文件夹路径存在:" + folderpath);
				        }
						FileUtils.copyFile(file,new File(folderpath,fileFileName));
						code="0";
						result="uploadsuccess";
					}
				}else{
					result = "uploaderror";
					code = "1";
					System.out.println("上传文件发生错误");
				}
			res = "{\"code\":\" " + code + " \",\"msg\":\"" + result + "\"}";
			return res;
	}
	
	
}
