package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.AdFilters;
import com.lexue.base.domain.AdTemplates;
import com.lexue.base.model.AdFilterTplModel;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.AdTemplateFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author lilong
 */
@Component
public class AdTemplateFeignHystrix implements AdTemplateFeignClient {
    @Override
    public ResultData<List<AdTemplates>> findAll(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdTemplates>> findPage(int pageIndex, int pageSize, String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageCount(String client) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> deleteAdTpl(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addAdTpl(AdTemplates adTemplates) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateAdTpl(AdTemplates adTemplates) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdTemplates> getAdTpl(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdTemplates> getAdTplToId(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addFilter(AdFilters adFilters) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdFilterTplModel> getAdFilterTpl(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdFilters> getAdFilter(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<AdFilters> getAdFilterToTplId(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateAdFilter(AdFilters adFilters) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<AdFilters>> findAllFilterToBoxId(String boxId) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}