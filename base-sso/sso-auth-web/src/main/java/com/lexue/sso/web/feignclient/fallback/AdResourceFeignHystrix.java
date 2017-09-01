package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.AdRes;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.AdResourceFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author lilong
 */
@Component
public class AdResourceFeignHystrix implements AdResourceFeignClient {
    @Override
    public ResultData<AdRes> getAdResource(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdRes>> findAll(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdRes>> findPage(int pageIndex, int pageSize, String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageCount(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> deleteAdResource(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addAdResource(AdRes adRes) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateAdResource(AdRes adRes) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdRes> getAdResourceToRsId(String rsId) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}