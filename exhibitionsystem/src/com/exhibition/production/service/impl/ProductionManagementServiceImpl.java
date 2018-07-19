package com.exhibition.production.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_type;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.dao.ProductionManagementDao;
import com.exhibition.production.service.ProductionManagementService;

/**
 * 作品的Service层实现层
 * 
 * @author LL
 *
 */
public class ProductionManagementServiceImpl implements ProductionManagementService {
	private ProductionManagementDao productionManagementDao;

	public void setProductionManagementDao(ProductionManagementDao productionManagementDao) {
		this.productionManagementDao = productionManagementDao;
	}

	@Override
	public List<ProductionDTO> shouPictures(String showAll) {
		List<ProductionDTO> listProductionDTO = new ArrayList<>();
		List<production_info> listInfo;
		ProductionDTO productionDTO;
		List<production_type> listproductiontype = (List<production_type>) productionManagementDao.listObject(
				"from production_type where production_type_isdelete='0' order by production_type_modifytime desc");
		/**
		 * 判断listproductiontype是不是为空
		 */
		if (!listproductiontype.isEmpty()) {
			/**
			 * 遍历类型表
			 */
			for (production_type production_type : listproductiontype) {
				listInfo = new ArrayList<>();
				productionDTO = new ProductionDTO();
				if (production_type.getProduction_type_id() != null
						&& production_type.getProduction_type_id().trim().length() > 0) {
					/**
					 * 查询每个类型的作品的集合
					 */
					listInfo = productionManagementDao.getProductionInfoById(production_type.getProduction_type_id());

					if (listInfo != null) {
						productionDTO.setListInfo(listInfo);
						listProductionDTO.add(productionDTO);
					}
				}
			}
		}
		return listProductionDTO;
	}

}
