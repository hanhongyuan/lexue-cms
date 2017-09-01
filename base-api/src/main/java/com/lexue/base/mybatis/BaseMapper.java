package com.lexue.base.mybatis;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 基类的mapper
 * 
 */
public interface BaseMapper<T> {
	/**
	 * 插入实体
	 * 
	 * @param t
	 * @return
	 */
	@InsertProvider(type = BaseSqlProvider.class, method = "insert")
	int insert(T t);

	/**
	 * 更新实体
	 * 
	 * @param t
	 * @return
	 */
	@UpdateProvider(type = BaseSqlProvider.class, method = "update")
	int update(T t);

	/**
	 * 根据ID删除实体
	 * 
	 * @param t
	 * @return
	 */
	@DeleteProvider(type = BaseSqlProvider.class, method = "deleteById")
	int deleteById(T t);

	/**
	 * 根据ID获取实体
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "getById")
	T getById(T t);

	/**
	 * 模糊匹配查询实体
	 * 
	 * @param dict
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "seleteVague")
	List<T> seleteVague(T t);

	/**
	 * 准确查询实体
	 * 
	 * @param dict
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "seleteAccuracy")
	List<T> seleteAccuracy(T t);

	/**
	 * 查询所有的实体
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "findAll")
	List<T> findAll(T t);

	/**
	 * 统计总数
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "countAll")
	long countAll(T t);

	/**
	 * 模糊匹配count
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "countVague")
	long countVague(T t);

	/**
	 * 精确匹配count
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "countAccuracy")
	long countAccuracy(T t);

	/**
	 * 删除所有的实体
	 * 
	 * @param t
	 * @return
	 */
	@DeleteProvider(type = BaseSqlProvider.class, method = "deleteAll")
	long deleteAll(T t);

	/**
	 * 模糊匹配删除
	 * 
	 * @param t
	 * @return
	 */
	@DeleteProvider(type = BaseSqlProvider.class, method = "deleteVague")
	long deleteVague(T t);

	/**
	 * 精确匹配删除
	 * 
	 * @param t
	 * @return
	 */
	@DeleteProvider(type = BaseSqlProvider.class, method = "deleteAccuracy")
	long deleteAccuracy(T t);

	/**
	 * 更新父IDs
	 * 
	 * @param t
	 * @return
	 */
	@UpdateProvider(type = BaseSqlProvider.class, method = "updateParentIds")
	int updateParentIds(T t);

	/**
	 * 根据父ID删除
	 * 
	 * @param t
	 * @return
	 */
	@DeleteProvider(type = BaseSqlProvider.class, method = "deleteByParentIdsLike")
	int deleteByParentIdsLike(T t);

	/**
	 * 根据ids查找
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "selectByIds")
	List<T> selectByIds(T t);

	/**
	 * 根据ids查找
	 * 
	 * @param t
	 * @return
	 */
	@SelectProvider(type = BaseSqlProvider.class, method = "deleteByIds")
	int deleteByIds(T t);

}
