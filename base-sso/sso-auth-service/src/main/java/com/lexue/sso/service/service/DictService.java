package com.lexue.sso.service.service;

import com.lexue.base.domain.Dict;
import com.lexue.base.mybatis.BaseService;
import com.lexue.sso.service.mapper.DictMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典的服务
 * <P>
 * 
 */
@Service
public class DictService extends BaseService<DictMapper, Dict> {

	@Transactional(readOnly = true)
	public List<String> getAllDictType() {
		return mapper.getAllType();
	}
	@Transactional(readOnly = true)
	public List<Dict> findPage(int pageIndex, int pageSize){
		return mapper.findPage((pageIndex-1)*pageSize,pageSize);
	}
	@Transactional(readOnly = true)
	public int findPageCount(){
		return mapper.findPageCount();
	}

	public List<Dict> findDictByTitle(String title){
		return mapper.findDictByTitle(title);
	}
	public String findDictByTitleAndValue(String title,String value){
		return mapper.findDictByTitleAndValue(title,value);
	}
}
