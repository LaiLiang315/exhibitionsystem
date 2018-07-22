package com.exhibition.carousel.service;

import java.util.List;

import com.exhibition.carousel.DTO.CarouselDTO;
import com.exhibition.carousel.DTO.CarouselManagementDTO;
import com.exhibition.domain.carousel;

/**
 * 轮播图管理的Service层接口
 * 
 * @author LL
 * @date 2018/07/17
 */
public interface CarouselManagementService {
	/**
	 * 查询轮播图
	 * @return
	 */
	public List<CarouselManagementDTO> querryCarousel();
/**
 * 批量删除轮播图
 * @param idList
 * @return
 */
	public String deleteCarousel(String idList);
/**
 * 添加轮播图
 * @param carousel
 * @return
 */
	public String addCarousel(carousel carousel);

	

}
