package com.exhibition.production.DTO;
/**
 * 最后一个DTO
 */
import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.domain.production_type;

public class InfoTypePhotoDTO {
	private production_type type;
	private production_info info;
	private production_pictures picture;
	public InfoTypePhotoDTO() {
		super();
	}
	
	public InfoTypePhotoDTO(production_type type, production_info info) {
		super();
		this.type = type;
		this.info = info;
	}

	public production_type getType() {
		return type;
	}

	public void setType(production_type type) {
		this.type = type;
	}

	public production_info getInfo() {
		return info;
	}

	public void setInfo(production_info info) {
		this.info = info;
	}

	public production_pictures getPicture() {
		return picture;
	}

	public void setPicture(production_pictures picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "InfoTypePhotoDTO [type=" + type + ", info=" + info + ", picture=" + picture + "]";
	}

}
