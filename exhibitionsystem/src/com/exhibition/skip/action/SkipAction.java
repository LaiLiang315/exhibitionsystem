package com.exhibition.skip.action;

public class SkipAction {
	private String data_id;
	
	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

	//进入后台管理界面
	public String intoBackground(){
		return "intoBackground";
	}
	
	//进入作品列表管理页面
	public String intoProductionList(){
		return "intoProductionList";
	}
	//进入添加作品页面
	public String intoProductionAdd(){
		return "intoProductionAdd";
	}
	//进入修改作品页面
	public String intoProductionEdit(){
		return "intoProductionEdit";
	}
	//进入分类列表管理页面
	public String intoCategoryList(){
		return "intoCategoryList";
	}
	//进入添加作品分类页面
	public String intoCategoryAdd(){
		return "intoCategoryAdd";
	}
	//进入修改作品分类页面
	public String intoCategoryEdit(){
		return "intoCategoryEdit";
	}
	//退出到登陆界面
	public String intoLogin(){
		return "intoLogin";
	}
}
