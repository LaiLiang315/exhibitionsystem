package com.exhibition.domain;

/**
 * 作品图集实体类
 * 
 * @author LL
 * @date 2018/07/18
 */
public class production_pictures {
	private String production_pictures_id; // 图集id
	private String production_pictures_name; // 图片名称
	private String production_pictures_belong; // 图集所属作品
	private Integer production_pictures_sequence; // 图集顺序
	private String production_pictures_creationtime; // 创建时间
	private String production_pictures_modifytime; // 修改时间
	private Integer production_pictures_isdelete; // 是否删除

	public String getProduction_pictures_id() {
		return production_pictures_id;
	}

	public void setProduction_pictures_id(String production_pictures_id) {
		this.production_pictures_id = production_pictures_id;
	}

	public String getProduction_pictures_name() {
		return production_pictures_name;
	}

	public void setProduction_pictures_name(String production_pictures_name) {
		this.production_pictures_name = production_pictures_name;
	}

	public String getProduction_pictures_belong() {
		return production_pictures_belong;
	}

	public void setProduction_pictures_belong(String production_pictures_belong) {
		this.production_pictures_belong = production_pictures_belong;
	}

	public Integer getProduction_pictures_sequence() {
		return production_pictures_sequence;
	}

	public void setProduction_pictures_sequence(Integer production_pictures_sequence) {
		this.production_pictures_sequence = production_pictures_sequence;
	}

	public String getProduction_pictures_creationtime() {
		return production_pictures_creationtime;
	}

	public void setProduction_pictures_creationtime(String production_pictures_creationtime) {
		this.production_pictures_creationtime = production_pictures_creationtime;
	}

	public String getProduction_pictures_modifytime() {
		return production_pictures_modifytime;
	}

	public void setProduction_pictures_modifytime(String production_pictures_modifytime) {
		this.production_pictures_modifytime = production_pictures_modifytime;
	}

	public Integer getProduction_pictures_isdelete() {
		return production_pictures_isdelete;
	}

	public void setProduction_pictures_isdelete(Integer production_pictures_isdelete) {
		this.production_pictures_isdelete = production_pictures_isdelete;
	}

	@Override
	public String toString() {
		return "production_pictures [production_pictures_id=" + production_pictures_id + ", production_pictures_name="
				+ production_pictures_name + ", production_pictures_belong=" + production_pictures_belong
				+ ", production_pictures_sequence=" + production_pictures_sequence
				+ ", production_pictures_creationtime=" + production_pictures_creationtime
				+ ", production_pictures_modifytime=" + production_pictures_modifytime
				+ ", production_pictures_isdelete=" + production_pictures_isdelete + "]";
	}

}
