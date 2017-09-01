package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Log;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.LogFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class LogFeignHystrix implements LogFeignClient {
    @Override
    public ResultData<Boolean> addLog(Log log) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<List<Log>> findPage(int pageIndex, int pageSize) {
        return ResultUtil.error(new ArrayList<Log>());
    }

    @Override
    public ResultData<Integer> findPageCount() {
        return ResultUtil.error(0);
    }
}
