package com.exhibition.productiontype.dao;

import java.util.List;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_type;

/**
 * 作品类型管理的Dao层
 * @author LL
 * @date 2018/07/18
 *
 */
 
public interface ProductionTypeDao {
	/**
	 * 保存、更新对象
	 * 
	 * @author ll
	 * @date 2018/07/16
	 * @param obj
	 * @modify ll 2018/07/16
	 */
	public void saveOrUpdateObject(Object obj);

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
	public List<?> queryForPage(String hql, int offset, int length);

	/**
	 * 获取对象总数量
	 * 
	 * @param hql
	 * @return
	 */
	public int getCount(String hql);

	/**
	 * 删除对象记录
	 * 
	 * @param obj
	 */
	public int removeObject(Object obj);

	/**
	 * 获取对象列表
	 */
	public List<?> listObject(String hql);

	public production_type getTypeById(String id);
/**
 * 根据Id查询轮播图
 * @param trim
 * @return
 */
	public carousel getCarouselById(String trim);


}
