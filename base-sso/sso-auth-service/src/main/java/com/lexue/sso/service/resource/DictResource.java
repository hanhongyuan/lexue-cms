package com.lexue.sso.service.resource;

import com.lexue.base.domain.Dict;
import com.lexue.base.domain.PageInfo;
import com.lexue.base.domain.Role;
import com.lexue.base.util.DateUtils;
import com.lexue.base.util.ResponseUtil;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictResource {

	@Autowired
	private DictService dictService;

	@PostMapping
	public ResultData<Boolean> addDict(@RequestBody Dict dict) {
		return ResultUtil.success(dictService.add(dict));
	}

	@PutMapping
	public ResultData<Boolean> updateDict(@RequestBody Dict dict) {
		return ResultUtil.success(dictService.update(dict));
	}

	@GetMapping
	public ResultData<List<Dict>> getAllDict() {
		return ResultUtil.success(dictService.findAll());
	}

	@GetMapping("/getDict")
	public ResultData<Dict>  getDict(@RequestHeader("id")  String id) {
		Dict dict = dictService.getById(id);
		return ResultUtil.success(dict);
	}

	@DeleteMapping("/deleteDict")
	public ResultData<Boolean> deleteDict(@RequestHeader("id")  String id) {
		return ResultUtil.success(dictService.deleteById(id));
	}

	@GetMapping("/getByType")
	public ResultData<List<Dict>> getByType(
			@RequestHeader("type")  String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return ResultUtil.success(dictService.seleteAccuracy(dict));
	}

	@GetMapping("/getAllType")
	public ResultData<List<Dict>> getAllType() {
		return ResultUtil.success(dictService.getAllDictType());
	}

	@GetMapping("/findPage")
	public ResultData<List<Dict>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize) {
		List<Dict> role = dictService.findPage(pageIndex,pageSize);
		return ResultUtil.success(role);
	}
	@GetMapping("/findPageCount")
	public ResultData<Integer> findPageCount(){
		return ResultUtil.success(dictService.findPageCount());
	}


	@GetMapping("/findDictByTitle")
	ResultData<List<Dict>> findDictByTitle(@RequestHeader("title") String title){
		return ResultUtil.success(dictService.findDictByTitle(title));
	}

	@GetMapping("/findDictByTitleAndValue")
	ResultData<String> findDictByTitleAndValue(@RequestHeader("title") String title,@RequestHeader("value") String value){
		return ResultUtil.success(dictService.findDictByTitleAndValue(title,value));
	}
}
