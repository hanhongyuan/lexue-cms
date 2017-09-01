package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Dict;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.DictFeifnHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典服务的远程调用的client
 * <P>
 * 
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = DictFeifnHystrix.class)
public interface DictFeignClient {


	/**
	 * 根据ID获取字典
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/dict/getDict")
	ResultData<Dict> getDict(@RequestHeader("id") String id);

	/**
	 * 根据ID删除字典
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/dict/deleteDict")
	ResultData<Boolean> deleteDict(@RequestHeader("id") String id);

	/**
	 * 添加字典
	 * 
	 * @param dict
	 * @return
	 */
	@PostMapping("/dict")
	ResultData<Boolean> addDict(@RequestBody(required = true) Dict dict);

	/**
	 * 修改字典
	 * 
	 * @param dict
	 * @return
	 */
	@PutMapping("/dict")
	ResultData<Boolean> updateDict(@RequestBody(required = true) Dict dict);

	/**
	 * 根据类别获取字典的list
	 * 
	 * @return
	 */
	@GetMapping("/dict/getByType")
	ResultData<List<Dict>> getByType(@RequestHeader("type") String type);

	/**
	 * 获取所有的字典类别
	 * 
	 * @return
	 */
	@GetMapping("/dict/getAllType")
	ResultData<List<String>> getAllType();

	@GetMapping("/dict/findPage")
	ResultData<List<Dict>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

	@GetMapping("/dict/findPageCount")
	ResultData<Integer> findPageCount();


	@GetMapping("/dict/findDictByTitle")
	ResultData<List<Dict>> findDictByTitle(@RequestHeader("title") String title);

	@GetMapping("/dict/findDictByTitleAndValue")
	ResultData<String> findDictByTitleAndValue(@RequestHeader("title") String title,@RequestHeader("value") String value);
}
