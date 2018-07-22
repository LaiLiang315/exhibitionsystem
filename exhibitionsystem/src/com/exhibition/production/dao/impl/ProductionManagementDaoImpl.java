package com.exhibition.production.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_info;
import com.exhibition.domain.production_pictures;
import com.exhibition.production.DTO.ProductionDTO;
import com.exhibition.production.DTO.ProductionThreeFormDTO;
import com.exhibition.production.dao.ProductionManagementDao;

/**
 * 作品的Dao层实现层
 * 
 * @author LL
 *
 */
public class ProductionManagementDaoImpl implements ProductionManagementDao {
	/**
	 * session注入
	 */
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 保存、更新对象
	 * 
	 * @author LL
	 * @date 2018/07/18
	 * @param obj
	 * @modify 2018/07/12
	 */
	@Override
	public void saveOrUpdateObject(Object obj) {
		Session session = getSession();
		session.saveOrUpdate(obj);
		session.flush();
	}

	/**
	 * 分页获取对象，这里是获取一页中的数据
	 * 
	 * @param hql
	 * @param offset
	 *            当前页
	 * @param length
	 *            获取每页记录数
	 * @return
	 */
	@Override
	public List<?> queryForPage(String hql, int offset, int length) {
		Session session = getSession();
		System.out.println("执行的hql:" + hql);
		Query query = session.createQuery(hql);
		query.setFirstResult((offset - 1) * length);
		query.setMaxResults(length);
		List<?> list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 获取对象总数量
	 * 
	 * @param hql
	 * @return
	 */
	@Override
	public int getCount(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		if (query.list().size() > 0) {
			return Integer.parseInt(query.list().get(0).toString());
		} else {
			return 0;
		}
	}

	/**
	 * 移除对象
	 */
	@Override
	public int removeObject(Object obj) {
		getSession().delete(obj);
		return 1;
	}

	/**
	 * 获取对象列表
	 */
	@Override
	public List<?> listObject(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<?> list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 根据id查询作品信息前六条
	 * 
	 */
	@Override
	public List<production_info> getProductionInfoById(String trim) {
		Session session = getSession();
		String hql = "from production_info where production_info_isdelete ='0' and production_info_type= :ID";
		Query query = session.createQuery(hql).setMaxResults(10);
		query.setParameter("ID", trim);
		List<production_info> productionInfo = (List<production_info>) query.list();
		return productionInfo;
	}

	/**
	 * 根据id查询所有作品前十条
	 */
	@Override
	public List<production_info> getProductionsInfoById(String trim) {
		Session session = getSession();
		String hql = "from production_info where production_info_isdelete ='0' and production_info_type= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		List<production_info> productionInfo = (List<production_info>) query.list();
		return productionInfo;
	}

	/**
	 * 根据Id查询图片
	 */

	@Override
	public List<production_pictures> getPictureInfoById(String trim) {
		Session session = getSession();
		String hql = "from production_pictures where production_pictures_isdelete ='0' and production_pictures_belong= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		List<production_pictures> listPicture = (List<production_pictures>) query.list();
		return listPicture;
	}

    /**
     * 根据id查询作品信息和类型
     * @param trim 
     */
	@Override
	public List<ProductionDTO> getInfoAndTypeById(String trim) {
		Session session = getSession();
		String hql = "select new com.exhibition.production.DTO.ProductionDTO(info,type) from production_info info, production_type type where production_info_type=production_type_id and production_info_id= :ID";
		System.out.println("::::::"+hql);
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		List<ProductionDTO> listProductionDTO = (List<ProductionDTO>) query.list();
		return listProductionDTO;
	}
	
	/**
	 * 根据id查询单个作品信息
	 * @param trim 
	 */
	@Override
	public ProductionThreeFormDTO getOnePrductionInfo(String trim) {
		ProductionThreeFormDTO productionThreeFormDTO = new ProductionThreeFormDTO();
		Session session = getSession();
		String hql = "select new com.exhibition.production.DTO.ProductionThreeFormDTO(info,type,listPicture) from production_info info, production_type type,production_pictures listPicture where info.production_info_type=type.production_type_id and listPicture.production_pictures_belong=info.production_info_id and info.production_info_id= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		productionThreeFormDTO = (ProductionThreeFormDTO) query.uniqueResult(); 
		return productionThreeFormDTO;

	}
	/**
	 * 根据信息id查询图集
	 * @param trim 
	 */
	@Override
	public List<production_pictures> getPicturesById(String trim){
		List<production_pictures> listPictures = new ArrayList<>();
		Session session = getSession();
		String hql = "from production_pictures where production_info_id= :ID order by production_pictures_sequence ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		listPictures = query.list();
		return listPictures;
		
		
	}
	
	/**
	 * 根据id查询作品信息
	 */
	@Override
	public production_info getInfoById(String trim) {
		production_info productionInfo = new production_info();
		Session session = getSession();
		String hql ="from production_info where  production_info_isdelete='0' and production_info_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		productionInfo =(production_info) query.uniqueResult();
		 return productionInfo;
	}
}
