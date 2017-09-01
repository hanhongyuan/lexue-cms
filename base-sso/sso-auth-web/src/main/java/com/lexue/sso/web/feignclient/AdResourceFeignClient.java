package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.AdBox;
import com.lexue.base.domain.AdRes;
import com.lexue.base.domain.AdResource;
import com.lexue.base.domain.Advertisement;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.AdResourceFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(name ="${spring.sso-auth-name}",fallback = AdResourceFeignHystrix.class)
public interface AdResourceFeignClient {

    @GetMapping("/adRes/getAdResource")
    ResultData<AdRes> getAdResource(@RequestHeader("id") String id);

    @GetMapping("/adRes/findAll")
    ResultData<List<AdRes>> findAll(@RequestHeader("client") String client);

    @GetMapping("/adRes/findPage")
    ResultData<List<AdRes>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize,@RequestHeader("client") String client);

    @GetMapping("/adRes/findPageCount")
    ResultData<Integer> findPageCount(@RequestHeader("client") String client);

    @DeleteMapping("/adRes/deleteAdResource")
    ResultData<Boolean> deleteAdResource(@RequestHeader("id") String id);

    @PostMapping("/adRes")
    ResultData<Boolean> addAdResource(@RequestBody(required = true) AdRes adRes);

    @PutMapping("/adRes")
    ResultData<Boolean> updateAdResource(@RequestBody(required = true) AdRes adRes);

    @GetMapping("/adRes/getAdResourceToRsId")
    ResultData<AdRes> getAdResourceToRsId(@RequestHeader("rsId") String rsId);
}