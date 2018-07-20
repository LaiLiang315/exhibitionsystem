package com.exhibition.skip.action;

public class SkipAction {

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
	//退出到登陆界面
	public String intoLogin(){
		return "intoLogin";
	}
}
