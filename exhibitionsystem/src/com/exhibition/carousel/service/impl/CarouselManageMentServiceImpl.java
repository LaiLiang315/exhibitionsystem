package com.exhibition.carousel.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.exhibition.carousel.DTO.CarouselDTO;
import com.exhibition.carousel.DTO.CarouselManagementDTO;
import com.exhibition.carousel.dao.CarouselManagementDao;
import com.exhibition.carousel.service.CarouselManagementService;
import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;

/**
 * 轮播图给管理的Service层实现层
 * 
 * @author LL
 * @date 2018/07/17
 */
public class CarouselManageMentServiceImpl implements CarouselManagementService {
	private CarouselManagementDao carouselManagementDao;

	public void setCarouselManagementDao(CarouselManagementDao carouselManagementDao) {
		this.carouselManagementDao = carouselManagementDao;
	}

	/**
	 * 查询轮播图
	 */

	@Override
	public List<CarouselManagementDTO> querryCarousel() {
		List<CarouselManagementDTO> listCarouselManagementDTO = new ArrayList<>();
		CarouselManagementDTO carouselManagementDTO;

		List<production_type> listproductiontype = (List<production_type>) carouselManagementDao.listObject(
				"from production_type where production_type_isdelete='0' order by production_type_modifytime desc");
		System.out.println("listproductiontype" + listproductiontype);
		/**
		 * 遍历轮播图表
		 */
		/**
		 * 如果listproductiontype不是空
		 */
		if (!listproductiontype.isEmpty()) {
			for (production_type production_type : listproductiontype) {
				carouselManagementDTO = new CarouselManagementDTO();
				if (production_type.getProduction_type_id() != null
						&& production_type.getProduction_type_id().trim().length() > 0) {
					/**
					 * 查询每个类型的所有轮播图
					 */
					List<carousel> listcarouselpicture = carouselManagementDao
							.getCarouselById(production_type.getProduction_type_id());
					System.out.println("carouselpicture" + listcarouselpicture);
					if (listcarouselpicture != null) {
						carouselManagementDTO.setListcarouselpicture(listcarouselpicture);
						carouselManagementDTO.setType(production_type);
						listCarouselManagementDTO.add(carouselManagementDTO);
					}
				}
			}
		}
		return listCarouselManagementDTO;
	}

	/**
	 * 批量删除轮播图
	 */
	@Override
	public String deleteCarousel(String idList) {
		if (idList != null && idList.trim().length() > 0) {
			/**
			 * 将多个对象id去掉分隔符转化为数组
			 */
			String[] deleteIdList = idList.split(",");
			/**
			 * 遍历数组
			 */
			for (String id : deleteIdList) {
				/**
				 * 如果轮播图表中存在需要删除的id
				 */
				if (carouselManagementDao.getCarouselById(id) != null) {
					List<carousel> listCarousel = new ArrayList<>();
					listCarousel = carouselManagementDao.getCarouselById(id);
                   if(listCarousel!=null) {
                	   
                	   
                   }
					return "deleteSuccess";
				}
				/**
				 * 如果数据库不存在需要删除的中转站的id
				 */
				else {
					return "error";
				}
			}

		}
		return null;
	}
/**
 * 添加轮播图
 */
	@Override
	public String addCarousel(carousel carousel) {
		
		
		
		return null;
	}
}
