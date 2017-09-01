package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Group;
import com.lexue.base.domain.WxGroup;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.WxGroupFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lilong on 17-8-3.
 */
@Component
public class WxFeignClient implements WxGroupFeignClient{
    @Override
    public ResultData<Boolean> addWxGroup(WxGroup wxGroup) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateWxGroup(WxGroup wxGroup) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<WxGroup>> findPage(int pageIndex, int pageSize) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageCount() {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<WxGroup> getWxGroup(String id) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> deleteWxGroup(String id) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> addGroup(Group group) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> updateGroup(Group group) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<Group>> findPageGroup(int pageIndex, int pageSize) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Integer> findPageGroupCount() {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Group> getGroup(String id) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<Boolean> deleteGroup(String id) {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<List<Group>> findGroupList() {
        return  ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}
