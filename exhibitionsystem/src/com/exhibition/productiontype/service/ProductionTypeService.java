package com.exhibition.productiontype.service;

import java.util.List;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;
import com.exhibition.productiontype.DTO.TypeCarouselDTO;

/**
 * 作品类型管理Service层
 * 
 * @author LL
 * @date 2018/07/18
 *
 */
public interface ProductionTypeService {
	/**
	 * 添加作品类型
	 * 
	 * @param productionType
	 * @param carousel
	 * @return
	 */
	/*
	 * public production_type addProductionType(production_type productionType,
	 * List<carousel> ListCarousel);
	 */

	String addProductionType(production_type productionType, carousel carousel);

	/**
	 * 批量删除类型
	 * 
	 * @param idList
	 * @return
	 */
	public String deleteProductionType(String idList);

	/**
	 * 修改类型
	 * 
	 * @param productionType
	 * @return
	 */

	public String updateProductionType(production_type productionType);

	public TypeCarouselDTO querryProductionType(TypeCarouselDTO typeCarouselDTO, production_type productionType);

}
