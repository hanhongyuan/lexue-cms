package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.AdFrames;
import com.lexue.base.domain.AdRela;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.AdFrameFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author lilong
 */
@Component
public class AdFrameFeignHystrix implements AdFrameFeignClient {
    @Override
    public ResultData<List<AdFrames>> findAll(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdFrames>> findPage(int pageIndex, int pageSize, String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageCount(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> deleteAdFrame(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addAdFrame(AdFrames adFrames) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateAdFrame(AdFrames adFrames) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdFrames> getAdFrame(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdFrames> getAdFrameToFmId(String fmId) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdFrames> getAdFrameAll(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addAdRela(AdRela adRela) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateAdRela(AdRela adRela) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdRela> getAdRela(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdRela> getAdRelaToFarmeId(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdFrames>> getAdFrameToTplId(String tplId) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdRela>> getAdRelaToRsId(String rsId) {
        return null;
    }
}