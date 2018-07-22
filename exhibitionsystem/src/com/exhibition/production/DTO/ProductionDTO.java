package com.exhibition.production.DTO;

import java.util.List;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_type;

/**
 * 作品信息DTO
 * 
 * @author LL
 *
 */
public class ProductionDTO {
	/**
	 * 作品信息表和作品类型表
	 */
	private production_info info;

	private production_type type;

	private List<production_info> listInfo;

	/**
	 * 构造方法
	 */
	public ProductionDTO() {
		super();
	}

	public production_info getInfo() {
		return info;
	}

	public void setInfo(production_info info) {
		this.info = info;
	}

	public production_type getType() {
		return type;
	}

	public void setType(production_type type) {
		this.type = type;
	}

	public List<production_info> getListInfo() {
		return listInfo;
	}

	public void setListInfo(List<production_info> listInfo) {
		this.listInfo = listInfo;
	}

	@Override
	public String toString() {
		return "ProductionDTO [info=" + info + ", type=" + type + ", listInfo=" + listInfo + "]";
	}

	public ProductionDTO(production_info info, production_type type) {
		super();
		this.info = info;
		this.type = type;
	}

}
