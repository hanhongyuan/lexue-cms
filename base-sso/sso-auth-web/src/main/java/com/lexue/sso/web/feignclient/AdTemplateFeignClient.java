package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.AdFilters;
import com.lexue.base.domain.AdTemplates;
import com.lexue.base.model.AdFilterTplModel;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.AdTemplateFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(name = "${spring.sso-auth-name}",fallback = AdTemplateFeignHystrix.class)
public interface AdTemplateFeignClient {

    @GetMapping("/adTpl/findAll")
    ResultData<List<AdTemplates>> findAll(@RequestHeader("client") String client);

    @GetMapping("/adTpl/findPage")
    ResultData<List<AdTemplates>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize,@RequestHeader("client") String client);

    @GetMapping("/adTpl/findPageCount")
    ResultData<Integer> findPageCount(@RequestHeader("client") String client);

    @DeleteMapping("/adTpl/deleteAdTpl")
    ResultData<Boolean> deleteAdTpl(@RequestHeader("id") String id);

    @PostMapping("/adTpl")
    ResultData<Boolean> addAdTpl(@RequestBody(required = true) AdTemplates adTemplates);

    @PutMapping("/adTpl")
    ResultData<Boolean> updateAdTpl(@RequestBody(required = true) AdTemplates adTemplates);

    @GetMapping("/adTpl/getAdTpl")
    ResultData<AdTemplates> getAdTpl(@RequestHeader("id") String id);

    @GetMapping("/adTpl/getAdTplToId")
    ResultData<AdTemplates> getAdTplToId(@RequestHeader("id") String id);

    @PostMapping("/adTpl/addFilter")
    ResultData<Boolean> addFilter(@RequestBody(required = true) AdFilters adFilters);

    @GetMapping("/adTpl/getAdFilterTpl")
    ResultData<AdFilterTplModel> getAdFilterTpl(@RequestHeader("id") String id);

    @GetMapping("/adTpl/getAdFilter")
    ResultData<AdFilters> getAdFilter(@RequestHeader("id") String id);

    @GetMapping("/adTpl/getAdFilterToTplId")
    ResultData<AdFilters> getAdFilterToTplId(@RequestHeader("id") String id);

    @PutMapping("/adTpl/updateAdFilter")
    ResultData<Boolean> updateAdFilter(@RequestBody(required = true) AdFilters adFilters);

    @GetMapping("/adTpl/findAllFilterToBoxId")
    ResultData<List<AdFilters>> findAllFilterToBoxId(@RequestHeader("boxId") String boxId);
}