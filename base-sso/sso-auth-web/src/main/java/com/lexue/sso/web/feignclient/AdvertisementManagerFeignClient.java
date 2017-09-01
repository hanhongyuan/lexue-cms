package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.AdBox;
import com.lexue.base.domain.ManagerResult;
import com.lexue.sso.web.feignclient.fallback.AdVertisementManageFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author lilong
 */
@FeignClient(name = "${spring.base-manager-service}",fallback = AdVertisementManageFeignHystrix.class)
public interface AdvertisementManagerFeignClient {

    @RequestMapping(value = {"/syncAd"},method = {RequestMethod.POST})
    ManagerResult<Boolean> syncAdvertisementManager(@RequestBody AdBox var1);

}