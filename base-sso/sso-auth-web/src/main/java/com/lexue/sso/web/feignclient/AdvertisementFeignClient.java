package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Advertisement;
import com.lexue.base.util.ResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(value = "${spring.sso-auth-name}")
public interface AdvertisementFeignClient {

    @GetMapping("/ad/findPage")
    ResultData<List<Advertisement>> findPage(@RequestHeader("pageIndex") int pageIndex, @RequestHeader("pageSize") int pageSize);

    @GetMapping("/ad/findPageCount")
    ResultData<Integer> findPageCount();

    @GetMapping("/ad/getAdvertisement")
    ResultData<Advertisement> getAdvertisement(@RequestHeader("id") String id);

    @DeleteMapping("/ad/deleteAdvertisement")
    ResultData<Boolean> deleteAdvertisement(@RequestHeader("id") String id);

    @PostMapping("/ad")
    ResultData<Boolean> addAdvertisement(@RequestBody(required = true) Advertisement advertisement);

    @PutMapping("/ad")
    ResultData<Boolean> updateAdvertisement(@RequestBody(required = true) Advertisement advertisement);
}