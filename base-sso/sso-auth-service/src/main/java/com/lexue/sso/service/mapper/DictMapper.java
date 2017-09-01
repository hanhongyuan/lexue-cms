package com.lexue.sso.service.mapper;

import com.lexue.base.domain.Dict;
import com.lexue.base.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字典的mapper映射
 * <P>
 * 
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
	/**
	 * 查询所有的字典的类别
	 * 
	 * @return
	 */
	@Select("select distinct(type) from sys_dict")
	List<String> getAllType();

	@Select("select * from sys_dict order by update_date desc limit ${pageIndex},${pageSize}")
	List<Dict> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	@Select("select count(*) from sys_dict")
	public int findPageCount();

	@Select("select label from sys_dict where type='${title}' and value='${value}' ")
	public String findDictByTitleAndValue(@Param("title")String title,@Param("value")String value);

	@Select("select * from sys_dict where type='${title}'  order by sort asc")
	List<Dict> findDictByTitle(@Param("title")String title);
}
