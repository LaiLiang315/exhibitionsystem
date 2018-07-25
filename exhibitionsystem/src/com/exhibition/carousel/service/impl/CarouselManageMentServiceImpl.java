package com.exhibition.carousel.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.exhibition.carousel.DTO.CarouselDTO;
import com.exhibition.carousel.DTO.CarouselManagementDTO;
import com.exhibition.carousel.dao.CarouselManagementDao;
import com.exhibition.carousel.service.CarouselManagementService;
import com.exhibition.domain.carousel;
import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.domain.production_type;

import util.BuildUuid;
import util.TimeUtil;

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
	 * 删除成功deleteSuccess 删除失败error 批量删除轮播图
	 */
	@Override
	public String deleteCarousel(String idList) {
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
				carousel carousel = new carousel();
				carousel = carouselManagementDao.getCarouselPictureById(id);

				System.out.println("AAAAA" + carousel);
				if (carousel != null) {
					carousel.setCarousel_isdelete(1);
					carousel.setCarousel_modifytime(TimeUtil.getStringSecond());
					System.out.println("DDDDDD" + carousel);
					carouselManagementDao.saveOrUpdateObject(carousel);
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
	 * 添加轮播图
	 */
	@Override
	public String addCarousel(carousel carousel) {
		if (carousel != null) {
			carousel.setCarousel_id(BuildUuid.getUuid());
			carousel.setCarousel_creationtime(TimeUtil.getStringSecond());
			carouselManagementDao.saveOrUpdateObject(carousel);
		}

		return null;
	}

	/**
	 * 删除作品图片 根据信息id查询图集 选择需要删除的图片 重新排序图集顺序
	 * 
	 */
	@Override
	public String deletePictures(String pictures) {
		String result = null;
		if (pictures != null && pictures.trim().length() > 0) {
			/**
			 * 将多个对象id去掉分隔符转化为数组
			 */
			production_pictures productionPictures = new production_pictures();
			productionPictures = carouselManagementDao.getPictureById(pictures);
			if (productionPictures != null) {
				productionPictures.setProduction_pictures_isdelete(1);
				productionPictures.setProduction_pictures_modifytime(TimeUtil.getStringSecond());
				List<production_pictures> listPictures = new ArrayList<>();
				listPictures = (List<production_pictures>) carouselManagementDao.listObject("from production_pictures where production_pictures_sequence>'"+productionPictures.getProduction_pictures_sequence()+"' and production_pictures_isdelete='0' and production_pictures_belong='"+productionPictures.getProduction_pictures_belong()+"'");
				System.out.println("MMMMMMMM"+listPictures);
				if(!listPictures.isEmpty()) {
					for (production_pictures production_pictures : listPictures) {
						production_pictures.setProduction_pictures_sequence(production_pictures.getProduction_pictures_sequence()-1);
						System.out.println("NNNNNN"+production_pictures);
						carouselManagementDao.saveOrUpdateObject(production_pictures);
					}
				}
				carouselManagementDao.saveOrUpdateObject(productionPictures);
				result = "deleteSuccess";
			} else {
				result = "error";
			}
		} else {
			result = "error";
		}
		return result;
	}
}
