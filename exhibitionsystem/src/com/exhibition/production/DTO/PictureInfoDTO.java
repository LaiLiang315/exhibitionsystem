package com.exhibition.production.DTO;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;

/**
 * 作品信息图片信息DTO
 * @author LL
 *
 */
public class PictureInfoDTO {
	/**
	 * 作品信息表
	 */
	private production_info Proinfo;
	/**
	 * 图片信息表
	 */
	private production_pictures Propicture;

	public production_info getProinfo() {
		return Proinfo;
	}

	public void setProinfo(production_info proinfo) {
		Proinfo = proinfo;
	}

	public production_pictures getPropicture() {
		return Propicture;
	}

	public void setPropicture(production_pictures propicture) {
		Propicture = propicture;
	}

	@Override
	public String toString() {
		return "PictureInfoDTO [Proinfo=" + Proinfo + ", Propicture=" + Propicture + "]";
	}

}
