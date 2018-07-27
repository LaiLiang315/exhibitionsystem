package com.exhibition.production.DTO;

import java.util.List;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;

/**
 * 作品信息和作品图集的DTO
 * 
 * @author LL
 *
 */
public class ProductionInfoDTO {
	/**
	 * 作品信息表
	 */
	private production_info productionInfo;
	/**
	 * 作品图集表
	 */
	private List<production_pictures> listProductionPictures;
	
	/**
	 * 作品图片
	 * @return
	 */
   private production_pictures picture;
	
	public production_info getProductionInfo() {
		return productionInfo;
	}

	public void setProductionInfo(production_info productionInfo) {
		this.productionInfo = productionInfo;
	}

	public List<production_pictures> getListProductionPictures() {
		return listProductionPictures;
	}

	public void setListProductionPictures(List<production_pictures> listProductionPictures) {
		this.listProductionPictures = listProductionPictures;
	}

	public production_pictures getPicture() {
		return picture;
	}

	public void setPicture(production_pictures picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "ProductionInfoDTO [productionInfo=" + productionInfo + ", listProductionPictures="
				+ listProductionPictures + ", picture=" + picture + "]";
	}


}
