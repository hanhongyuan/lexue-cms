package com.lexue.sso.service.resource;

import com.lexue.base.domain.Dict;
import com.lexue.base.domain.Log;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.service.service.DictService;
import com.lexue.sso.service.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogResource {

	@Autowired
	private LogService logService;

	@PostMapping
	public ResultData<Boolean> addLog(@RequestBody Log log) {
		return ResultUtil.success(logService.add(log));
	}

	@GetMapping("/findPage")
	public ResultData<List<Log>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize) {
		List<Log> role = logService.findPage(pageIndex,pageSize);
		return ResultUtil.success(role);
	}
	@GetMapping("/findPageCount")
	public ResultData<Integer> findPageCount(){
		return ResultUtil.success(logService.findPageCount());
	}
}
