package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.AdBox;
import com.lexue.base.domain.AdBoxs;
import com.lexue.base.domain.AdResource;
import com.lexue.base.domain.Advertisement;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.AdBoxFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = AdBoxFeignHystrix.class)
public interface AdBoxFeignClient {

    @GetMapping("/adBox/findAll")
    ResultData<List<AdBoxs>> findAll(@RequestHeader("client") String client);

    @GetMapping("/adBox/findPage")
    ResultData<List<AdBoxs>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize, @RequestHeader("client") String client);

    @GetMapping("/adBox/findPageCount")
    ResultData<Integer> findPageCount( @RequestHeader("client") String client);

    @DeleteMapping("/adBox/deleteAdBox")
    ResultData<Boolean> deleteAdBox(@RequestHeader("id") String id);

    @PostMapping("/adBox")
    ResultData<Boolean> addAdBox(@RequestBody(required = true) AdBoxs adBox);

    @PutMapping("/adBox")
    ResultData<Boolean> updateAdBox(@RequestBody(required = true) AdBoxs adBox);


    @GetMapping("/adBox/getAdBoxs")
    ResultData<AdBoxs> getAdBoxs(@RequestHeader("id") String id);

    @GetMapping("/adBox/getAdBoxsToBoxId")
    ResultData<AdBoxs> getAdBoxsToBoxId(@RequestHeader("id") String id);
    @GetMapping("/adBox/getAdBoxsToFmId")
    ResultData<AdBoxs> getAdBoxsToFmId(@RequestHeader("fmId") String fmId);
}