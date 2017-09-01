package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Log;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.LogFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户sso的远程调用的client
 * 
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = LogFeignHystrix.class)
public interface LogFeignClient {

	@PostMapping("/log")
	ResultData<Boolean> addLog(@RequestBody(required = true) Log log);

	@GetMapping("/log/findPage")
	ResultData<List<Log>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

	@GetMapping("/log/findPageCount")
	ResultData<Integer> findPageCount();
}
