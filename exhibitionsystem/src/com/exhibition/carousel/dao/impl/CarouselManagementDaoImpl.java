package com.exhibition.carousel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.exhibition.carousel.dao.CarouselManagementDao;
import com.exhibition.domain.carousel;
import com.exhibition.domain.production_pictures;

/**
 * 轮播图管理的Dao层实现层
 * 
 * @author LL
 * @date 2018/07/17
 */
public class CarouselManagementDaoImpl implements CarouselManagementDao {
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
	 * 根据id查询轮播图片集合
	 * 
	 */
	@Override
	public List<carousel> getCarouselById(String trim) {
		Session session = getSession();
		String hql = "from carousel where carousel_isshow='1'and carousel_isdelete='0' and carousel_belong = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		List<carousel> carousel = (List<carousel>) query.list();
		session.clear();
		return carousel;
	}
	/**
	 * 根据id获取图片
	 */
	@Override
	public carousel getCarouselPictureById(String trim) {
		carousel carousel = new carousel();
		Session session = getSession();
		String hql ="from carousel where carousel_isshow='1'and carousel_isdelete='0' and carousel_id= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		carousel =(carousel) query.uniqueResult();
		 return carousel;
	}
	/**
	 * 根据Id查询图片
	 */
	@Override
	public production_pictures getPictureById(String trim) {
		production_pictures pictures = new production_pictures();
		Session session = getSession();
		String hql ="from production_pictures where production_pictures_isdelete='0' and production_pictures_id= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		pictures =(production_pictures) query.uniqueResult();
		 return pictures;
	}
	/**
	 * 根据信息id查询图集
	 * @return
	 */
	@Override
	public List<production_pictures> getLaterPicturesById(String trim){
		List<production_pictures> listPictures = new ArrayList<>();
		production_pictures productionPictures = new production_pictures();
		Session session = getSession();
		String hql = "from production_pictures where production_pictures_isdelete='0' and production_pictures_belong= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		query.setFirstResult(productionPictures.getProduction_pictures_sequence());
		if(!listPictures.isEmpty()) {
			listPictures =(List<production_pictures>) query.list();
		}
		return listPictures;
	}

}
