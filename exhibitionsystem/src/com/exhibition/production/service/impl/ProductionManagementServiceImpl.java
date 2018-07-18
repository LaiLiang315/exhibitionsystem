package com.exhibition.production.service.impl;

import com.exhibition.production.dao.ProductionManagementDao;
import com.exhibition.production.service.ProductionManagementService;

/**
 * 作品的Service层实现层
 * @author LL
 *
 */
public class ProductionManagementServiceImpl implements ProductionManagementService{
 private ProductionManagementDao productionManagementDao;

public void setProductionManagementDao(ProductionManagementDao productionManagementDao) {
	this.productionManagementDao = productionManagementDao;
}
 
}
