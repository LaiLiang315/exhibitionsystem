package com.exhibition.carousel.service;

import java.util.List;

import com.exhibition.carousel.DTO.CarouselDTO;
import com.exhibition.carousel.DTO.CarouselManagementDTO;

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

	

}
