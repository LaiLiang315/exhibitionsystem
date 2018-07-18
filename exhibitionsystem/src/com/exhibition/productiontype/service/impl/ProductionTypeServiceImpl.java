package com.exhibition.productiontype.service.impl;

import com.exhibition.productiontype.dao.ProductionTypeDao;
import com.exhibition.productiontype.service.ProductionTypeService;

/**
 * 作品类型管理Service层实现层
 * @author LL
 * @date 2018/07/18
 *
 */
public class ProductionTypeServiceImpl implements ProductionTypeService {
	private ProductionTypeDao productionTypeDao;

	public void setProductionTypeDao(ProductionTypeDao productionTypeDao) {
		this.productionTypeDao = productionTypeDao;
	}

}
