package com.exhibition.carousel.DTO;

import java.util.List;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;

/**
 * 轮播图表和作品类别DTO
 * 
 * @author LL
 *
 */
public class CarouselManagementDTO {
	/**
	 * 作品类型表中 作品类型
	 */
	private production_type type;

	/**
	 * 轮播图表中轮播图片
	 */
	private List<carousel> listcarouselpicture;

	public production_type getType() {
		return type;
	}

	public void setType(production_type type) {
		this.type = type;
	}

	public List<carousel> getListcarouselpicture() {
		return listcarouselpicture;
	}

	public void setListcarouselpicture(List<carousel> listcarouselpicture) {
		this.listcarouselpicture = listcarouselpicture;
	}

	@Override
	public String toString() {
		return "CarouselManagementDTO [type=" + type + ", listcarouselpicture=" + listcarouselpicture + "]";
	}

	

	

}
