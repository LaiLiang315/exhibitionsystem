package com.exhibition.production.service;

import java.util.List;

import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.VO.ProductionVO;

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

/**
 * 显示图片的DTO
 * @param showAll
 * @return
 */
	public List<ProductionDTO> showPicturesDTO(String showAll);
/**
 * 显示图片的分页
 * @param showAll
 * @param productionVO 
 * @return
 */
	public ProductionVO showPicturesVO(String showAll, ProductionVO productionVO);


}
