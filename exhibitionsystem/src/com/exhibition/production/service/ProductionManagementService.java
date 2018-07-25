package com.exhibition.production.service;

import java.util.List;
import java.util.Map;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.production.DTO.PicTypeInfoDTO;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionInfoDTO;
import com.exhibition.production.DTO.ProductionThreeFormDTO;
import com.exhibition.production.VO.ProductionVO;
import com.google.gson.JsonElement;

/**
 * 作品的Service层接口
 * 
 * @author LL
 *
 */
public interface ProductionManagementService {

	/**
	 * 显示多少张图片
	 * 
	 * @param showAll
	 * @return
	 */

	/**
	 * 显示图片的DTO
	 * 
	 * @param showAll
	 * @return
	 */
	public List<ProductionDTO> showPicturesDTO(String showAll);

	/**
	 * 显示图片的分页
	 * 
	 * @param showAll
	 * @param productionVO
	 * @return
	 */
	public ProductionVO showPicturesVO(String showAll, ProductionVO productionVO);

	/**
	 * 查询每个图集的信息
	 * 
	 * @return
	 */
	public List<ProductionInfoDTO> getProductionInfo();

	/**
	 * 查询所有类型信息
	 * 
	 * @param productionVO
	 * @return
	 */
	public ProductionVO querryAllProduction(ProductionVO productionVO);

	/**
	 * 添加作品
	 * 
	 * @param productionInfo
	 * @return
	 */
	public String addProduction(production_info productionInfo, List<production_pictures> production_pictures);

	/**
	 * 查询单个作品信息
	 * 
	 * @param productionInfo
	 * @return
	 */
	public ProductionThreeFormDTO querryOneProduction(production_info productionInfo);

	/**
	 * 批量删除作品
	 * 
	 * @param idList
	 * @return
	 */

	public String deleteProduction(String idList);

	/**
	 * 修改作品
	 * 
	 * @param productionInfo
	 * @return
	 */
	public String updateProdction(production_info productionInfo);

	/**
	 * 添加图集
	 * 
	 * @param production_picture
	 */

	public void addPictrues(production_pictures production_picture);

	/**
	 * 添加作品信息完善图集信息
	 * 
	 * @param productionInfo
	 * @param listMap
	 */
	public String addAndComplete(production_info productionInfo, List<Map<String, Object>> listMap);

	/**
	 * 查询六条平时作业
	 * @return
	 */
	public PicTypeInfoDTO querrySixProduction();

}
