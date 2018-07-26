package com.exhibition.production.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.domain.production_type;
import com.exhibition.production.DTO.PicTypeInfoDTO;
import com.exhibition.production.DTO.PictureInfoDTO;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionInfoDTO;
import com.exhibition.production.DTO.ProductionThreeFormDTO;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.dao.ProductionManagementDao;
import com.exhibition.production.service.ProductionManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.BuildUuid;
import util.TimeUtil;

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
		//
		List<PictureInfoDTO> listPictureInfoDTO = new ArrayList<>();
		List<production_pictures> firstPicure;
		List<PicTypeInfoDTO> listPicTypeInfoDTO = new ArrayList<>();
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

					if (!listInfo.isEmpty()) {
						for (production_info info : listInfo) {
							PictureInfoDTO pictureInfoDTO = new PictureInfoDTO();
							production_pictures PicureOne = productionManagementDao
									.getFistPictureById(info.getProduction_info_id());
							System.out.println("firstPicure" + PicureOne);
							if (PicureOne.getProduction_pictures_id() != null) {
								pictureInfoDTO.setProinfo(info);
								pictureInfoDTO.setPropicture(PicureOne);
								listPictureInfoDTO.add(pictureInfoDTO);
							}

						}
						PicTypeInfoDTO PicTypeInfoDTO = new PicTypeInfoDTO();
						PicTypeInfoDTO.setType(production_type);
						PicTypeInfoDTO.setListPictureInfoDTO(listPictureInfoDTO);
						listPicTypeInfoDTO.add(PicTypeInfoDTO);
						/*
						 * productionDTO.setListInfo(listInfo); productionDTO.setType(production_type);
						 */
						System.out.println("zzzzzz" + productionDTO);

					} else {
						return null;
					}
				}
			}
			/**
			 * 分页获取单位列表
			 */
			productionVO.setList(listPicTypeInfoDTO);
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
						listProductionInfoDTO.add(productionInfoDTO);
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
		String listProductionHql = "";
		String productionCountHql = "";
		listProductionHql = "select new com.exhibition.production.DTO.ProductionDTO(info,type) from production_info info, production_type type where production_info_isdelete='0' and production_info_type=production_type_id";
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
		listProductionDTO = (List<ProductionDTO>) productionManagementDao.queryForPage(listProductionHql,
				productionVO.getPageIndex(), productionVO.getPageSize());

		System.out.println("+++___________________" + listProductionDTO);
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

	/**
	 * 添加作品
	 */
	@Override
	public String addProduction(production_info productionInfo, List<production_pictures> production_pictures) {
		String id = BuildUuid.getUuid();
		String result = null;
		if (productionInfo != null) {
			production_info production = new production_info();
			production.setProduction_info_id(id);
			production.setProduction_info_name(productionInfo.getProduction_info_name());
			production.setProduction_info_author(productionInfo.getProduction_info_author());
			production.setProduction_info_discription(productionInfo.getProduction_info_discription());
			production.setProduction_info_type(productionInfo.getProduction_info_type());
			production.setProduction_info_creationtime(productionInfo.getProduction_info_creationtime());
			production.setProduction_info_isdelete(0);
			productionManagementDao.saveOrUpdateObject(production);
			result = "addSuccess";
		} else {
			result = "addFailed";
		}
		if (!production_pictures.isEmpty()) {
			for (production_pictures pic : production_pictures) {
				pic.setProduction_pictures_id(BuildUuid.getUuid());
				pic.setProduction_pictures_belong(id);
				pic.setProduction_pictures_creationtime(TimeUtil.getStringSecond());
				pic.setProduction_pictures_isdelete(0);
				productionManagementDao.saveOrUpdateObject(pic);
			}
			result = "addSuccess";
		} else {
			result = "addFailed";
		}
		return result;
	}

	/**
	 * 查询条信息（图集，类型，信息）
	 */

	@Override
	public ProductionThreeFormDTO querryOneProduction(production_info productionInfo) {
		ProductionThreeFormDTO productionThreeFormDTO = new ProductionThreeFormDTO();
		ProductionDTO productionDTO;
		List<production_pictures> listPictures;
		productionDTO = productionManagementDao.getOnePrductionInfo(productionInfo.getProduction_info_id());
		System.out.println("MMMMMMM" + productionDTO);

		if (productionDTO != null) {
			List<production_pictures> pictures = productionManagementDao
					.getPictureInfoById(productionInfo.getProduction_info_id());
			System.out.println("///////" + pictures);
			if (pictures != null) {
				productionThreeFormDTO.setListPicture(pictures);
				productionThreeFormDTO.setProductionDTO(productionDTO);

				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.setPrettyPrinting();// 格式化json数据
				Gson gson = gsonBuilder.create();
				System.out.println("*********" + gson.toJson(productionThreeFormDTO));
			}

		}

		return productionThreeFormDTO;

	}

	/**
	 * 批量删除
	 */
	@Override
	public String deleteProduction(String idList) {
		String result = null;
		if (idList != null && idList.trim().length() > 0) {
			/**
			 * 将多个对象id去掉分隔符转化为数组
			 */
			String[] deleteIdList = idList.split(",");
			for (String id : deleteIdList) {
				production_info productionInfo = new production_info();
				productionInfo = productionManagementDao.getInfoById(id);
				if (productionInfo != null) {
					productionInfo.setProduction_info_isdelete(1);
					productionInfo.setProduction_info_modifytime(TimeUtil.getStringSecond());
					productionManagementDao.saveOrUpdateObject(productionInfo);
					result = "deleteSuccess";
				} else {
					result = "error";
				}
			}
		} else {
			result = "error";
		}
		return result;
	}

	/**
	 * 更改作品信息 更改成功 "success" 更改失败 "fail"
	 */
	@Override
	public String updateProdction(production_info productionInfo) {
		String result = null;
		production_info info = new production_info();
		if (productionInfo.getProduction_info_id() != null
				&& productionInfo.getProduction_info_id().trim().length() > 0) {

			info = productionManagementDao.getInfoById(productionInfo.getProduction_info_id());
			if (info != null) {
				info.setProduction_info_name(productionInfo.getProduction_info_name());
				info.setProduction_info_author(productionInfo.getProduction_info_author());
				info.setProduction_info_discription(productionInfo.getProduction_info_discription());
				info.setProduction_info_type(productionInfo.getProduction_info_type());
				info.setProduction_info_modifytime(TimeUtil.getStringSecond());
				productionManagementDao.saveOrUpdateObject(info);
				result = "success";
			} else {
				result = "fail";
			}
		}
		return result;
	}

	/**
	 * 添加图片
	 */
	@Override
	public void addPictrues(production_pictures production_picture) {
		// TODO Auto-generated method stub
		production_picture.setProduction_pictures_id(BuildUuid.getUuid());
		// 将图集顺序设置为特殊值，便去后面补充信息是重置
		production_picture.setProduction_pictures_sequence(9999);
		production_picture.setProduction_pictures_creationtime(TimeUtil.getStringSecond());
		production_picture.setProduction_pictures_isdelete(0);
		productionManagementDao.saveOrUpdateObject(production_picture);
	}

	/**
	 * 添加作品信息完善图集信息
	 */
	@Override
	public String addAndComplete(production_info productionInfo, List<Map<String, Object>> listMap) {
		// TODO Auto-generated method stub
		// 首先添加作品信息
		// 生成uuid
		String result = null;
		String productionId = BuildUuid.getUuid();
		productionInfo.setProduction_info_id(productionId);
		productionInfo.setProduction_info_isdelete(0);
		productionManagementDao.saveOrUpdateObject(productionInfo);
		for (int i = 0; i < listMap.size(); i++) {
			String pictrueName = (String) listMap.get(i).get("key");
			String sequence = (String) listMap.get(i).get("value");
			int se = Integer.parseInt(sequence);
			// 查询出带有特殊标记的图集信息
			List<production_pictures> listproduction_pictures = productionManagementDao.getSpectialPic(pictrueName);
			if (listproduction_pictures.size() > 0) {
				production_pictures mypicture = listproduction_pictures.get(0);
				mypicture.setProduction_pictures_belong(productionId);
				mypicture.setProduction_pictures_sequence(se);
				try {
					productionManagementDao.saveOrUpdateObject(mypicture);
					result = "success";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result = "error";
				}
			} else {
				result = "error";
			}
		}
		return result;
	}

	/**
	 * 查询六条平时作业
	 */
	@Override
	public List<PicTypeInfoDTO> querrySixProduction() {
		List<PicTypeInfoDTO> listPicTypeInfoDTO = new ArrayList<>();
		List<ProductionInfoDTO> listProductionInfoDTO = new ArrayList<>();
		PicTypeInfoDTO picTypeInfoDTO = new PicTypeInfoDTO();
		List<production_type> listType = new ArrayList<>();
		ProductionInfoDTO productionInfoDTO = new ProductionInfoDTO();
		listType = (List<production_type>) productionManagementDao.listObject("from production_type where 1=1");
		if (!listType.isEmpty()) {

			for (production_type production_type : listType) {
				List<production_info> listInfo = new ArrayList<>();
				listInfo = (List<production_info>) productionManagementDao
						.getSixProductionInfoById(production_type.getProduction_type_id());
				
				if (!listInfo.isEmpty()) {
					for (production_info production_info : listInfo) {
						List<production_pictures> pictureFirst = new ArrayList<>();
						pictureFirst =(List<production_pictures>) productionManagementDao.getFistPictureById(production_info.getProduction_info_id());
						if(!pictureFirst.isEmpty()) {
							productionInfoDTO.setListProductionPictures(pictureFirst);
							productionInfoDTO.setProductionInfo(production_info);
							listProductionInfoDTO.add(productionInfoDTO);
						}
					}
					picTypeInfoDTO.setListProductionInfoDTO(listProductionInfoDTO);
					picTypeInfoDTO.setType(production_type);
				}
			}
		}
		listPicTypeInfoDTO.add(picTypeInfoDTO);
		return listPicTypeInfoDTO;
	}
	//作品修改方法（仅修改作品信息）
	@Override
	public String updateProductionInfo(production_info productionInfo) {
		// TODO Auto-generated method stub
		productionInfo.setProduction_info_modifytime(TimeUtil.getStringSecond());
		try {
			productionManagementDao.saveOrUpdateObject(productionInfo);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String updateProductionAndPicInfo(production_info productionInfo, List<Map<String, Object>> listMap) {
		// 首先添加作品信息
				// 生成uuid
				String result = null;
				productionInfo.setProduction_info_isdelete(0);
				productionInfo.setProduction_info_modifytime(TimeUtil.getStringSecond());
				productionManagementDao.saveOrUpdateObject(productionInfo);
				for (int i = 0; i < listMap.size(); i++) {
					String pictrueName = (String) listMap.get(i).get("key");
					String sequence = (String) listMap.get(i).get("value");
					int se = Integer.parseInt(sequence);
					// 查询出带有特殊标记的图集信息
					List<production_pictures> listproduction_pictures = productionManagementDao.getSpectialPic(pictrueName);
					if (listproduction_pictures.size() > 0) {
						production_pictures mypicture = listproduction_pictures.get(0);
						mypicture.setProduction_pictures_belong(productionInfo.getProduction_info_id());
						mypicture.setProduction_pictures_sequence(se);
						try {
							productionManagementDao.saveOrUpdateObject(mypicture);
							result = "success";
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							result = "error";
						}
					} else {
						result = "error";
					}
				}
				return result;
	}
}
