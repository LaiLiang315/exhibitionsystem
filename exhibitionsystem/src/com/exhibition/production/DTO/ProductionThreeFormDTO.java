package com.exhibition.production.DTO;

import java.util.List;

import com.exhibition.domain.production_pictures;

/**
 * 查询单条信息的类型图集
 * 
 * @author LL
 *
 */
public class ProductionThreeFormDTO {
	
	private ProductionDTO productionDTO;
	/**
	 * 图片表集合
	 */
	private List<production_pictures> listPicture;
	
	public ProductionDTO getProductionDTO() {
		return productionDTO;
	}
	public void setProductionDTO(ProductionDTO productionDTO) {
		this.productionDTO = productionDTO;
	}
	public List<production_pictures> getListPicture() {
		return listPicture;
	}
	public void setListPicture(List<production_pictures> listPicture) {
		this.listPicture = listPicture;
	}
	
	public ProductionThreeFormDTO() {
		super();
	}
	public ProductionThreeFormDTO(ProductionDTO productionDTO, List<production_pictures> listPicture) {
		super();
		this.productionDTO = productionDTO;
		this.listPicture = listPicture;
	}
	
}
