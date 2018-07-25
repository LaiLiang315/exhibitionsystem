package com.exhibition.production.DTO;

import java.util.List;

import com.exhibition.domain.production_type;

/**
 * 信息图片类型DTO
 * 
 * @author LL
 *
 */
public class PicTypeInfoDTO {
	/**
	 * 类型表
	 */
	private production_type type;
	/**
	 * PictureInfoDTO
	 */
	private List<PictureInfoDTO> listPictureInfoDTO;
	
	private List<ProductionInfoDTO> listProductionInfoDTO;

	public production_type getType() {
		return type;
	}

	public void setType(production_type type) {
		this.type = type;
	}

	public List<PictureInfoDTO> getListPictureInfoDTO() {
		return listPictureInfoDTO;
	}

	public void setListPictureInfoDTO(List<PictureInfoDTO> listPictureInfoDTO) {
		this.listPictureInfoDTO = listPictureInfoDTO;
	}

	public List<ProductionInfoDTO> getListProductionInfoDTO() {
		return listProductionInfoDTO;
	}

	public void setListProductionInfoDTO(List<ProductionInfoDTO> listProductionInfoDTO) {
		this.listProductionInfoDTO = listProductionInfoDTO;
	}

	@Override
	public String toString() {
		return "PicTypeInfoDTO [type=" + type + ", listPictureInfoDTO=" + listPictureInfoDTO
				+ ", listProductionInfoDTO=" + listProductionInfoDTO + "]";
	}

}
