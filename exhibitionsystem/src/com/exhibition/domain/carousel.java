package com.exhibition.domain;

/**
 * 轮播图实体类
 * 
 * @author LL
 * @date 2018/07/18
 */
public class carousel {
	private String carousel_id; // 轮播图图片id
	private String carousel_belong; // 轮播图所属作品分类
	private Integer carousel_isshow; // 轮播图是否展示
	private String carousel_picture; // 轮播图片
	private String carousel_creationtime; // 创建时间
	private String carousel_modifytime; // 修改时间
	private Integer carousel_isdelete; // 轮播图是否删除
	public String getCarousel_id() {
		return carousel_id;
	}
	public void setCarousel_id(String carousel_id) {
		this.carousel_id = carousel_id;
	}
	public String getCarousel_belong() {
		return carousel_belong;
	}
	public void setCarousel_belong(String carousel_belong) {
		this.carousel_belong = carousel_belong;
	}
	public Integer getCarousel_isshow() {
		return carousel_isshow;
	}
	public void setCarousel_isshow(Integer carousel_isshow) {
		this.carousel_isshow = carousel_isshow;
	}
	public String getCarousel_picture() {
		return carousel_picture;
	}
	public void setCarousel_picture(String carousel_picture) {
		this.carousel_picture = carousel_picture;
	}
	public String getCarousel_creationtime() {
		return carousel_creationtime;
	}
	public void setCarousel_creationtime(String carousel_creationtime) {
		this.carousel_creationtime = carousel_creationtime;
	}
	public String getCarousel_modifytime() {
		return carousel_modifytime;
	}
	public void setCarousel_modifytime(String carousel_modifytime) {
		this.carousel_modifytime = carousel_modifytime;
	}
	public Integer getCarousel_isdelete() {
		return carousel_isdelete;
	}
	public void setCarousel_isdelete(Integer carousel_isdelete) {
		this.carousel_isdelete = carousel_isdelete;
	}
	@Override
	public String toString() {
		return "carousel [carousel_id=" + carousel_id + ", carousel_belong=" + carousel_belong + ", carousel_isshow="
				+ carousel_isshow + ", carousel_picture=" + carousel_picture + ", carousel_creationtime="
				+ carousel_creationtime + ", carousel_modifytime=" + carousel_modifytime + ", carousel_isdelete="
				+ carousel_isdelete + "]";
	}

}
