package com.exhibition.production.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.domain.production_type;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionInfoDTO;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.dao.ProductionManagementDao;
import com.exhibition.production.service.ProductionManagementService;

/**
 * 作品的Service层实现层
 * 
 * @author LL
 *
 */
public class ProductionManagementServiceImpl implements ProductionManagementService {
	private ProductionManagementDao productionManagementDao;

	public void setProductionManagementDao(ProductionManagementDao productionManagementDao) {
		this.productionManagementDao = productionManagementDao;
	}

	/**
	 * 显示图片的DTO
	 */
	@Override
	public List<ProductionDTO> showPicturesDTO(String showAll) {
		List<ProductionDTO> listProductionDTO = new ArrayList<>();
		List<production_info> listInfo;
		ProductionDTO productionDTO;
		List<production_type> listproductiontype = (List<production_type>) productionManagementDao.listObject(
				"from production_type where production_type_isdelete='0' order by production_type_modifytime desc");
		/**
		 * 判断listproductiontype是不是为空
		 */
		if (!listproductiontype.isEmpty()) {
			/**
			 * 遍历类型表
			 */
			for (production_type production_type : listproductiontype) {
				listInfo = new ArrayList<>();
				productionDTO = new ProductionDTO();
				if (production_type.getProduction_type_id() != null
						&& production_type.getProduction_type_id().trim().length() > 0) {
					/**
					 * 查询每个类型的作品的集合
					 */
					listInfo = productionManagementDao.getProductionInfoById(production_type.getProduction_type_id());
					if (listInfo != null) {
						productionDTO.setListInfo(listInfo);
						listProductionDTO.add(productionDTO);
					}
				}
			}
		}
		return listProductionDTO;
	}

	/**
	 * 显示图片的分页
	 */
	@Override
	public ProductionVO showPicturesVO(String showAll, ProductionVO productionVO) {
		// 实例化List<ProductionDTO>
		List<ProductionDTO> listProductionDTO = new ArrayList<>();
		// 实例化ProductionDTO
		ProductionDTO productionDTO;
		// 实例化List<production_info>
		List<production_info> listInfo;
		String listProductionHql = "from production_info where 1=1";
		String productionCountHql = "select count(*) from production_info where 1=1";
		/**
		 * 根据作者和名称模糊查询
		 */
		System.out.println("AAAAAAA");
		if (productionVO.getSearch() != null && productionVO.getSearch().trim().length() > 0) {
			String search = "%" + productionVO.getSearch().trim() + "%";
			productionCountHql = productionCountHql + " and (production_info_name like '" + search + "'";
			listProductionHql = listProductionHql + " and (production_info_name like '" + search + "'";
			productionCountHql = productionCountHql + " or production_info_author like '" + search + "')";
			listProductionHql = listProductionHql + " or production_info_author like '" + search + "')";
			System.out.println("qaqaqa");
		}

		/**
		 * 根据Type查询
		 */
		if (productionVO.getType() != null && productionVO.getType().trim().length() > 0) {
			System.out.println("CCCCCC");
			productionCountHql = productionCountHql + " and production_info_type = '" + productionVO.getType().trim()
					+ "'";
			System.out.println("DDDDD" + productionCountHql);
			listProductionHql = listProductionHql + " and production_info_type = '" + productionVO.getType().trim()
					+ "'";
			System.out.println("EEEEE" + listProductionHql);

		}
		listInfo = (List<production_info>) productionManagementDao.queryForPage(listProductionHql,
				productionVO.getPageIndex(), productionVO.getPageSize());
		// 这里如果不加desc表示正序，如果加上desc表示倒序
		productionCountHql = productionCountHql + " order by production_info_creationtime desc";
		int productionCount = productionManagementDao.getCount(productionCountHql);
		// 设置总数量
		productionVO.setTotalRecords(productionCount);
		// 设置总页数
		productionVO.setTotalPages(((productionCount - 1) / productionVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (productionVO.getPageIndex() <= 1) {
			productionVO.setHavePrePage(false);
		} else {
			productionVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (productionVO.getPageIndex() >= productionVO.getTotalPages()) {

			productionVO.setHaveNextPage(false);
		} else {
			productionVO.setHaveNextPage(true);
		}
		// 如果showAll=0，默认显示前六条
		if (showAll.equals("0")) {
			// 查询所有类型
			List<production_type> listproductiontype = (List<production_type>) productionManagementDao.queryForPage(
					"from production_type where production_type_isdelete='0' order by production_type_modifytime desc",
					productionVO.getPageIndex(), productionVO.getPageSize());
			// 遍历类型表
			for (production_type production_type : listproductiontype) {
				System.out.println("HHHHHHH");
				listInfo = new ArrayList<>();
				productionDTO = new ProductionDTO();
				if (production_type.getProduction_type_id() != null
						&& production_type.getProduction_type_id().trim().length() > 0) {
					/**
					 * 查询每个类型的作品的集合
					 */
					listInfo = productionManagementDao.getProductionInfoById(production_type.getProduction_type_id());
					if (listInfo != null) {
						productionDTO.setListInfo(listInfo);
						productionDTO.setType(production_type);
						System.out.println("zzzzzz" + productionDTO);

					} else {
						return null;
					}
				}

				listProductionDTO.add(productionDTO);
			}
			/**
			 * 分页获取单位列表
			 */
			productionVO.setListProductionDTO(listProductionDTO);
			System.out.println("--------------" + productionVO);
			return productionVO;
		} else if (showAll.equals("1")) {
			// 查询所有类型
			List<production_type> listproductiontype = (List<production_type>) productionManagementDao.queryForPage(
					"from production_type where production_type_isdelete='0' order by production_type_modifytime desc",
					productionVO.getPageIndex(), productionVO.getPageSize());
			System.out.println("DDDDDDD" + listproductiontype);
			// 遍历类型表
			for (production_type production_type : listproductiontype) {
				System.out.println("qzdsdwqda");
				listInfo = new ArrayList<>();
				productionDTO = new ProductionDTO();
				if (production_type.getProduction_type_id() != null
						&& production_type.getProduction_type_id().trim().length() > 0) {
					/**
					 * 查询每个类型的作品的集合
					 */
					listInfo = productionManagementDao.getProductionsInfoById(production_type.getProduction_type_id());
					if (listInfo != null) {
						productionDTO.setListInfo(listInfo);
						productionDTO.setType(production_type);
					} else {
						return null;
					}
				}
				listProductionDTO.add(productionDTO);
				System.out.println("123123234" + listProductionDTO);
			}
		} else {
			return null;
		}
		/**
		 * 分页获取单位列表
		 */
		productionVO.setListProductionDTO(listProductionDTO);
		return productionVO;
	}

	/**
	 * 显示每条信息的图集
	 */
	@Override
	public List<ProductionInfoDTO> getProductionInfo() {
		List<ProductionInfoDTO> listProductionInfoDTO = new ArrayList<>();
		List<production_pictures> listPictures;
		ProductionInfoDTO productionInfoDTO;
		List<production_info> listInfo = (List<production_info>) productionManagementDao.listObject(
				"from production_info where production_info_isdelete='0' order by production_info_modifytime desc");

		if (!listInfo.isEmpty()) {
			for (production_info production_info : listInfo) {
				listPictures = new ArrayList<>();
				productionInfoDTO = new ProductionInfoDTO();
				if (production_info.getProduction_info_id() != null
						&& production_info.getProduction_info_id().trim().length() > 0) {
					/**
					 * 得到每种信息的图集
					 */
					listPictures = productionManagementDao.getPictureInfoById(production_info.getProduction_info_id());

					if (listPictures != null) {
						productionInfoDTO.setListProductionPictures(listPictures);
						productionInfoDTO.setProductionInfo(production_info);
						System.out.println("AAAAAA" + productionInfoDTO);
						listProductionInfoDTO.add(productionInfoDTO);
						System.out.println("BBBBBBB" + listProductionInfoDTO);
					}
				}
			}
		}

		return null;
	}

	@Override
	public ProductionVO querryAllProduction(ProductionVO productionVO) {
		// 实例化List<ProductionDTO>
		List<ProductionDTO> listProductionDTO = new ArrayList<>();
		// 实例化ProductionDTO
		ProductionDTO productionDTO;
		// 实例化List<production_info>
		List<production_info> listInfo;
		String listProductionHql = "";
		String productionCountHql = "";
		listProductionHql = "select new com.exhibition.production.DTO.ProductionDTO(info,type) from production_info info, production_type type where 1=1";
		productionCountHql = "select count(*) from production_info where 1=1";
		/**
		 * 根据作者和作品名模糊查询
		 */
		if (productionVO.getSearch() != null && productionVO.getSearch().trim().length() > 0) {
			String search = "%" + productionVO.getSearch().trim() + "%";
			productionCountHql = productionCountHql + " and (production_info_name like '" + search + "'";
			listProductionHql = listProductionHql + " and (info.production_info_name like '" + search + "'";
			productionCountHql = productionCountHql + " or production_info_author like '" + search + "')";
			listProductionHql = listProductionHql + " or info.production_info_author like '" + search + "')";
			System.out.println("+++++++++");
		}
		/**
		 * 根据Type查询
		 */
		if (productionVO.getType() != null && productionVO.getType().trim().length() > 0) {
			System.out.println("KKKKKAAAA");
			productionCountHql = productionCountHql + " and production_info_type = '" + productionVO.getType().trim()
					+ "'";
			System.out.println("YYYYYYDDDDD" + productionCountHql);
			listProductionHql = listProductionHql + " and production_info_type = '" + productionVO.getType().trim()
					+ "'";
			System.out.println("ZZZZZEEEEE" + listProductionHql);

		}
		listInfo = (List<production_info>) productionManagementDao.queryForPage(listProductionHql,
				productionVO.getPageIndex(), productionVO.getPageSize());
		
		System.out.println("+++___________________"+listInfo);
		// 这里如果不加desc表示正序，如果加上desc表示倒序
		productionCountHql = productionCountHql + " order by production_info_creationtime desc";
		int productionCount = productionManagementDao.getCount(productionCountHql);
		// 设置总数量
		productionVO.setTotalRecords(productionCount);
		// 设置总页数
		productionVO.setTotalPages(((productionCount - 1) / productionVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (productionVO.getPageIndex() <= 1) {
			productionVO.setHavePrePage(false);
		} else {
			productionVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (productionVO.getPageIndex() >= productionVO.getTotalPages()) {

			productionVO.setHaveNextPage(false);
		} else {
			productionVO.setHaveNextPage(true);
		}
		productionVO.setListProductionDTO(listProductionDTO);
		return productionVO;
	}
}
