package com.exhibition.production.dao;

import java.util.List;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_info;

/**
 * 作品的Dao层
 * 
 * @author LL
 *
 */
public interface ProductionManagementDao {
	/**
	 * 保存、更新对象
	 * 
	 * @author LL
	 * @date 2018/07/18
	 * @param obj
	 * @modify 2018/07/12
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
/**
 * 根据ID查询作品信息
 * @param trim
 * @return
 */
	public List<production_info> getProductionInfoById(String trim);

}
