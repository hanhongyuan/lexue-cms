package com.lexue.sso.web.feignclient;

import com.lexue.base.domain.Video;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.fallback.VideoFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * author lilong
 */
@FeignClient(name = "VIDEOBASE-SERVICE",fallback = VideoFeignHystrix.class)
public interface VideoFeignClient {

    @GetMapping("/video/findAll")
    ResultData<List<Video>> findAllVideo(@RequestHeader("client")String client);
}