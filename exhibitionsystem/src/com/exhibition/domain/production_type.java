package com.exhibition.domain;

/**
 * 作品类型实体类
 * 
 * @author LL
 * @date 2018/07/18
 */
public class production_type {
	private String production_type_id; //作品类型id
	private String production_type_name; //类别名称
	private String production_type_logo; //类别图标
	private String production_type_title; //轮播图片上标题
	private String production_type_discription; //轮播图片上正文
	private String production_type_picture; //轮播图png图片
	private String production_type_creationtime; //创建时间
	private String production_type_modifytime; //修改时间
	private Integer production_type_isdelete; //类别是否删除
	public String getProduction_type_id() {
		return production_type_id;
	}
	public void setProduction_type_id(String production_type_id) {
		this.production_type_id = production_type_id;
	}
	public String getProduction_type_name() {
		return production_type_name;
	}
	public void setProduction_type_name(String production_type_name) {
		this.production_type_name = production_type_name;
	}
	public String getProduction_type_logo() {
		return production_type_logo;
	}
	public void setProduction_type_logo(String production_type_logo) {
		this.production_type_logo = production_type_logo;
	}
	public String getProduction_type_title() {
		return production_type_title;
	}
	public void setProduction_type_title(String production_type_title) {
		this.production_type_title = production_type_title;
	}
	public String getProduction_type_discription() {
		return production_type_discription;
	}
	public void setProduction_type_discription(String production_type_discription) {
		this.production_type_discription = production_type_discription;
	}
	public String getProduction_type_picture() {
		return production_type_picture;
	}
	public void setProduction_type_picture(String production_type_picture) {
		this.production_type_picture = production_type_picture;
	}
	public String getProduction_type_creationtime() {
		return production_type_creationtime;
	}
	public void setProduction_type_creationtime(String production_type_creationtime) {
		this.production_type_creationtime = production_type_creationtime;
	}
	public String getProduction_type_modifytime() {
		return production_type_modifytime;
	}
	public void setProduction_type_modifytime(String production_type_modifytime) {
		this.production_type_modifytime = production_type_modifytime;
	}
	public Integer getProduction_type_isdelete() {
		return production_type_isdelete;
	}
	public void setProduction_type_isdelete(Integer production_type_isdelete) {
		this.production_type_isdelete = production_type_isdelete;
	}
	@Override
	public String toString() {
		return "production_type [production_type_id=" + production_type_id + ", production_type_name="
				+ production_type_name + ", production_type_logo=" + production_type_logo + ", production_type_title="
				+ production_type_title + ", production_type_discription=" + production_type_discription
				+ ", production_type_picture=" + production_type_picture + ", production_type_creationtime="
				+ production_type_creationtime + ", production_type_modifytime=" + production_type_modifytime
				+ ", production_type_isdelete=" + production_type_isdelete + "]";
	}

}
