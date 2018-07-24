package com.exhibition.productiontype.service.impl;

import java.util.Arrays;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;
import com.exhibition.productiontype.DTO.TypeCarouselDTO;
import com.exhibition.productiontype.dao.ProductionTypeDao;
import com.exhibition.productiontype.service.ProductionTypeService;

import util.BuildUuid;
import util.TimeUtil;

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

		if (productionType != null && carousel != null) {
			productionType.setProduction_type_id(BuildUuid.getUuid());
			productionType.setProduction_type_creationtime(TimeUtil.getStringSecond());
			productionType.setProduction_type_isdelete(0);
			carousel.setCarousel_creationtime(TimeUtil.getStringSecond());
			carousel.setCarousel_id(BuildUuid.getUuid());
			carousel.setCarousel_belong(productionType.getProduction_type_id());
			carousel.setCarousel_isdelete(0);
			productionTypeDao.saveOrUpdateObject(productionType);

		}else {
			
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
			System.out.println(Arrays.toString(deleteIdList) + "uuuu" + deleteIdList[1]);
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
					System.out.println("DDDDDD" + type);
					productionTypeDao.saveOrUpdateObject(type);
					System.out.println("=======");
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
		return null;
	}
/**
 * 查询所有类型
 */
	@Override
	public TypeCarouselDTO querryProductionType(TypeCarouselDTO typeCarouselDTO,production_type productionType) {
		TypeCarouselDTO TypeCarouselDTONew = new TypeCarouselDTO();
		carousel carousel = new carousel();
		production_type productionTypeNew = new production_type();
		carousel = productionTypeDao.getCarouselById(productionType.getProduction_type_id());
		if(carousel!=null) {
			TypeCarouselDTONew.setCarousel(carousel);
		}
		productionTypeNew = productionTypeDao.getTypeById(productionType.getProduction_type_id());
		if(productionTypeNew!=null) {
			TypeCarouselDTONew.setType(productionTypeNew);
		}
		return TypeCarouselDTONew;
	}
}
