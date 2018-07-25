package com.exhibition.carousel.dao;

import java.util.List;

import com.exhibition.domain.carousel;
import com.exhibition.domain.production_pictures;

/**
 * 轮播图管理的Dao层接口
 * @author LL
 * @date 2018/07/17
 */
public interface CarouselManagementDao {
	/**
	 * 保存、更新对象
	 * 
	 * @author LL
	 * @date 2018/07/18
	 * @param obj
	 * @modify  2018/07/12
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
	 * 根据id查询轮播图集合
	 * @param trim
	 * @return
	 */

	public List<carousel> getCarouselById(String trim);
	/**
	 * 根据id查询轮播图
	 * @param trim
	 * @return
	 */
	public carousel getCarouselPictureById(String trim);
/**
 * 根据id的查询图片
 * @param trim
 * @return
 */
	 public production_pictures getPictureById(String trim);
  

	List<production_pictures> getLaterPicturesById(String trim);
}
