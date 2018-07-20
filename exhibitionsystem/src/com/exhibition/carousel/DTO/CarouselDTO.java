package com.exhibition.carousel.DTO;

import com.exhibition.domain.carousel;

/**
 * 轮播图表
 * 
 * @author LL
 *
 */
public class CarouselDTO {
	/**
	 * 轮播图所属类型
	 */
	private carousel carousel_belong;

	public carousel getCarousel_belong() {
		return carousel_belong;
	}

	public void setCarousel_belong(carousel carousel_belong) {
		this.carousel_belong = carousel_belong;
	}

	@Override
	public String toString() {
		return "CarouselDTO [carousel_belong=" + carousel_belong + "]";
	}

}
