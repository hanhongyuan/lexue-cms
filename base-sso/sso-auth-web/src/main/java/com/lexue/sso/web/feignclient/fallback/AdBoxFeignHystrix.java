package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.AdBoxs;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.AdBoxFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author lilong
 */
@Component
public class AdBoxFeignHystrix implements AdBoxFeignClient {
    @Override
    public ResultData<List<AdBoxs>> findAll(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdBoxs>> findPage(int pageIndex, int pageSize, String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageCount(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> deleteAdBox(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addAdBox(AdBoxs adBox) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateAdBox(AdBoxs adBox) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdBoxs> getAdBoxs(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdBoxs> getAdBoxsToBoxId(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
    @Override
    public ResultData<AdBoxs> getAdBoxsToFmId(String fmId) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}