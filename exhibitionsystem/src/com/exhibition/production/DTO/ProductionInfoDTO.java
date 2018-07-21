package com.exhibition.production.DTO;

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
	private production_pictures productionPictures;

	public production_info getProductionInfo() {
		return productionInfo;
	}

	public void setProductionInfo(production_info productionInfo) {
		this.productionInfo = productionInfo;
	}

	public production_pictures getProductionPictures() {
		return productionPictures;
	}

	public void setProductionPictures(production_pictures productionPictures) {
		this.productionPictures = productionPictures;
	}

	@Override
	public String toString() {
		return "ProductionInfoDTO [productionInfo=" + productionInfo + ", productionPictures=" + productionPictures
				+ "]";
	}

}
