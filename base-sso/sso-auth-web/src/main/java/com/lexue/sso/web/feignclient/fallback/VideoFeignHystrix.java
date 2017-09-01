package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Video;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.VideoFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author lilong
 */
@Component
public class VideoFeignHystrix implements VideoFeignClient {
    @Override
    public ResultData<List<Video>> findAllVideo(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}