package com.exhibition.carousel.service.impl;

import com.exhibition.carousel.dao.CarouselManagementDao;
import com.exhibition.carousel.service.CarouselManagementService;

/**
 * 轮播图给管理的Service层实现层
 * @author LL
 * @date 2018/07/17
 */
public class CarouselManageMentServiceImpl implements CarouselManagementService{
	private CarouselManagementDao carouselManagementDao;

	public void setCarouselManagementDao(CarouselManagementDao carouselManagementDao) {
		this.carouselManagementDao = carouselManagementDao;
	}
	

}
