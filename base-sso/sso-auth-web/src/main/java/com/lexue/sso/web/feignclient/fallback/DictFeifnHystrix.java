package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Dict;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.DictFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class DictFeifnHystrix implements DictFeignClient {
    @Override
    public ResultData<Dict> getDict(String id) {
        return ResultUtil.error(new Dict());
    }

    @Override
    public ResultData<Boolean> deleteDict(String id) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<Boolean> addDict(Dict dict) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<Boolean> updateDict(Dict dict) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<List<Dict>> getByType(String type) {
        return ResultUtil.error(new ArrayList<Dict>());
    }

    @Override
    public ResultData<List<String>> getAllType() {
        return ResultUtil.error(new ArrayList<String>());
    }

    @Override
    public ResultData<List<Dict>> findPage(int pageIndex, int pageSize) {
        return ResultUtil.error(new ArrayList<Dict>());
    }

    @Override
    public ResultData<Integer> findPageCount() {
        return ResultUtil.error(0);
    }

    @Override
    public ResultData<List<Dict>> findDictByTitle(String title) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<String> findDictByTitleAndValue(String title, String value) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}
