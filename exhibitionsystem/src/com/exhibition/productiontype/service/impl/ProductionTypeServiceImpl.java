package com.exhibition.productiontype.service.impl;

import java.util.Arrays;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;
import com.exhibition.productiontype.DTO.TypeCarouselDTO;
import com.exhibition.productiontype.dao.ProductionTypeDao;
import com.exhibition.productiontype.service.ProductionTypeService;

import util.BuildUuid;
import util.TimeUtil;
import util.uploadFiles;

/**
 * 作品类型管理Service层实现层
 * 
 * @author LL
 * @date 2018/07/18
 *
 */
public class ProductionTypeServiceImpl implements ProductionTypeService {
	private ProductionTypeDao productionTypeDao;

	public void setProductionTypeDao(ProductionTypeDao productionTypeDao) {
		this.productionTypeDao = productionTypeDao;
	}

	/**
	 * 添加类型
	 */
	@Override
	public String addProductionType(production_type productionType, carousel carousel) {
		String result = null;

		if (productionType != null) {
			productionType.setProduction_type_id(BuildUuid.getUuid());
			productionType.setProduction_type_creationtime(TimeUtil.getStringSecond());
			productionType.setProduction_type_isdelete(0);
			productionTypeDao.saveOrUpdateObject(productionType);
			result = "success";
		} else {
			result = "error";
		}

		if (carousel != null) {
			carousel.setCarousel_creationtime(TimeUtil.getStringSecond());
			carousel.setCarousel_id(BuildUuid.getUuid());
			carousel.setCarousel_belong(productionType.getProduction_type_id());
			carousel.setCarousel_isdelete(0);
			productionTypeDao.saveOrUpdateObject(carousel);
			result = "success";
		} else {
			result = "error";
		}
		return result;
	}

	// 单独添加轮播
	@Override
	public String addCarousel1(carousel carousel) {
		String result = null;
		if (carousel != null) {
			productionTypeDao.saveOrUpdateObject(carousel);
			result = "success";
		} else {
			result = "error";
		}
		return result;
	}

	// 单独添加类型
	@Override
	public String addProductionType1(production_type productionType) {
		String result = null;
		if (productionType != null) {
			productionTypeDao.saveOrUpdateObject(productionType);
			result = "success";
		} else {
			result = "error";
		}
		return result;
	}

	/**
	 * 删除成功deleteSuccess 删除失败error 删除类型
	 */

	@Override
	public String deleteProductionType(String idList) {
		String result = null;
		if (idList != null && idList.trim().length() > 0) {
			/**
			 * 将多个对象id去掉分隔符转化为数组
			 */
			String[] deleteIdList = idList.split(",");
			// System.out.println(Arrays.toString(deleteIdList) + "uuuu" + deleteIdList[1]);
			/**
			 * 遍历数组String id : deleteIdList
			 */
			for (String id : deleteIdList) {
				System.out.println("111111" + deleteIdList);
				production_type type = new production_type();
				type = productionTypeDao.getTypeById(id);
				System.out.println("AAAAA" + type);
				if (type != null) {
					type.setProduction_type_isdelete(1);
					type.setProduction_type_modifytime(TimeUtil.getStringSecond());

					carousel carousel = new carousel();
					carousel = productionTypeDao.getCarouselById(id);
					if (carousel != null) {
						carousel.setCarousel_isdelete(1);
						productionTypeDao.saveOrUpdateObject(carousel);
					}

					productionTypeDao.saveOrUpdateObject(type);
					result = "deleteSuccess";
				} else {
					result = "error";
				}
				/**
				 * 如果数据库不存在需要删除的中转站的id
				 */
			}
		} else {
			result = "error";
		}

		return result;
	}

	/**
	 * 修改类型
	 */
	@Override
	public String updateProductionType(production_type productionType) {
		if (productionType != null) {
			productionType.setProduction_type_modifytime(TimeUtil.getStringSecond());
			productionTypeDao.saveOrUpdateObject(productionType);
		}
		carousel carousel = new carousel();
		carousel = productionTypeDao.getCarouselById(productionType.getProduction_type_id());
		if(carousel!=null) {
			carousel.setCarousel_isshow(1);
			carousel.setCarousel_modifytime(TimeUtil.getStringSecond());
			carousel.setCarousel_isdelete(0);
			productionTypeDao.saveOrUpdateObject(carousel);
		}
		return null;
	}
		

	
	/**
	 * 查询单个类型和轮播图
	 */
	@Override
	public TypeCarouselDTO querryProductionType(production_type productionType) {
		TypeCarouselDTO TypeCarouselDTONew = new TypeCarouselDTO();
		production_type productionTypeNew = new production_type();
		TypeCarouselDTONew = productionTypeDao.getNewCarouselById(productionType.getProduction_type_id());
		System.out.println("AAAAAAA"+TypeCarouselDTONew);
		return TypeCarouselDTONew;
	}
/**
 * 修改类型
 */
	@Override
	public String updateType(production_type productionType) {
		if (productionType != null) {
			productionType.setProduction_type_modifytime(TimeUtil.getStringSecond());
			productionTypeDao.saveOrUpdateObject(productionType);
		}
		
		return null;
	}
/**
 * 修改轮播图
 */
	@Override
	public String updateCarousel(production_type productionType) {
		carousel carousel = new carousel();
		carousel = productionTypeDao.getCarouselById(productionType.getProduction_type_id());
		if(carousel!=null) {
			carousel.setCarousel_isshow(1);
			carousel.setCarousel_modifytime(TimeUtil.getStringSecond());
			carousel.setCarousel_isdelete(0);
			productionTypeDao.saveOrUpdateObject(carousel);
		}
		return null;
	}

}
