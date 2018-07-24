package com.exhibition.productiontype.DTO;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;

/**
 * 类型和轮播图DTO
 * 
 * @author LL
 *
 */
public class TypeCarouselDTO {
	/**
	 * 轮播图表
	 */
	private carousel carousel;
	/**
	 * 类型表
	 */
	private production_type type;

	public carousel getCarousel() {
		return carousel;
	}

	public void setCarousel(carousel carousel) {
		this.carousel = carousel;
	}

	public production_type getType() {
		return type;
	}

	public void setType(production_type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TypeCarouselDTO [carousel=" + carousel + ", type=" + type + "]";
	}

}
