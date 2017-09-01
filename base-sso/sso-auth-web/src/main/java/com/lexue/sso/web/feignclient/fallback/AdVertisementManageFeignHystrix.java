package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.AdBox;
import com.lexue.base.domain.ManagerResult;
import com.lexue.sso.web.feignclient.AdvertisementManagerFeignClient;
import org.springframework.stereotype.Component;

/**
 * author lilong
 */
@Component
public class AdVertisementManageFeignHystrix implements AdvertisementManagerFeignClient {
    @Override
    public ManagerResult<Boolean> syncAdvertisementManager(AdBox var1) {
        return new ManagerResult(500,"调用远程服务错误");
    }
}