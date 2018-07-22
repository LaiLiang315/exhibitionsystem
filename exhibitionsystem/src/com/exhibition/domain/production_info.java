package com.exhibition.domain;

/**
 * 作品信息的实体类
 * 
 * @author LL
 * @date 2018/07/18
 *
 */
public class production_info {
	private String production_info_id; // 作品id
	private String production_info_name; // 作品名字
	private String production_info_author; // 作品作者
	private String production_info_discription; // 作品介绍
	private String production_info_type; // 作品类型
	private String production_info_creationtime; // 创建时间
	private String production_info_modifytime; // 修改时间
	private int production_info_isdelete; // 是否删除

	public String getProduction_info_id() {
		return production_info_id;
	}

	public void setProduction_info_id(String production_info_id) {
		this.production_info_id = production_info_id;
	}

	public String getProduction_info_name() {
		return production_info_name;
	}

	public void setProduction_info_name(String production_info_name) {
		this.production_info_name = production_info_name;
	}

	public String getProduction_info_author() {
		return production_info_author;
	}

	public void setProduction_info_author(String production_info_author) {
		this.production_info_author = production_info_author;
	}

	public String getProduction_info_discription() {
		return production_info_discription;
	}

	public void setProduction_info_discription(String production_info_discription) {
		this.production_info_discription = production_info_discription;
	}

	public String getProduction_info_type() {
		return production_info_type;
	}

	public void setProduction_info_type(String production_info_type) {
		this.production_info_type = production_info_type;
	}

	public String getProduction_info_creationtime() {
		return production_info_creationtime;
	}

	public void setProduction_info_creationtime(String production_info_creationtime) {
		this.production_info_creationtime = production_info_creationtime;
	}

	public String getProduction_info_modifytime() {
		return production_info_modifytime;
	}

	public void setProduction_info_modifytime(String production_info_modifytime) {
		this.production_info_modifytime = production_info_modifytime;
	}

	public int getProduction_info_isdelete() {
		return production_info_isdelete;
	}

	public void setProduction_info_isdelete(int production_info_isdelete) {
		this.production_info_isdelete = production_info_isdelete;
	}

	@Override
	public String toString() {
		return "production_info [production_info_id=" + production_info_id + ", production_info_name="
				+ production_info_name + ", production_info_author=" + production_info_author
				+ ", production_info_discription=" + production_info_discription + ", production_info_type="
				+ production_info_type + ", production_info_creationtime=" + production_info_creationtime
				+ ", production_info_modifytime=" + production_info_modifytime + ", production_info_isdelete="
				+ production_info_isdelete + "]";
	}
}
