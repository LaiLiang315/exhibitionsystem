package com.exhibition.production.service;

import java.util.List;

import com.exhibition.production.DTO.ProductionDTO;

/**
 * 作品的Service层接口
 * @author LL
 *
 */
public interface ProductionManagementService {

	/**
	 * 显示多少张图片
	 * @param showAll
	 * @return
	 */
	public List<ProductionDTO> shouPictures(String showAll);

}
