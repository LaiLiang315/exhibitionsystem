package com.exhibition.production.DTO;

import java.util.List;

import com.exhibition.domain.production_type;

/**
 * 查询六条平时作品DTO
 * 
 * @author LL
 *
 */
public class QuerrySixDailyWorkDTO {

	private production_type production_type; // 类型表
	private List<PictureInfoDTO> ListPictureInfoDTO; // 图片表和信息表的DTO

	public production_type getProduction_type() {
		return production_type;
	}

	public void setProduction_type(production_type production_type) {
		this.production_type = production_type;
	}

	public List<PictureInfoDTO> getListPictureInfoDTO() {
		return ListPictureInfoDTO;
	}

	public void setListPictureInfoDTO(List<PictureInfoDTO> listPictureInfoDTO) {
		ListPictureInfoDTO = listPictureInfoDTO;
	}

	@Override
	public String toString() {
		return "QuerrySixDailyWorkDTO [production_type=" + production_type + ", ListPictureInfoDTO="
				+ ListPictureInfoDTO + "]";
	}

}
