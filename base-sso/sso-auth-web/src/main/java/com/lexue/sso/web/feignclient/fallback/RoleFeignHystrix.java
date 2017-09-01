package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Role;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.RoleFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class RoleFeignHystrix implements RoleFeignClient {
    @Override
    public ResultData<Role> getRole(String id) {
        return ResultUtil.error(new Role());
    }

    @Override
    public ResultData<List<Role>> getAllRole() {
        return ResultUtil.error(new ArrayList<Role>());
    }

    @Override
    public ResultData<List<Role>> findPage(int pageIndex, int pageSize) {
        return ResultUtil.error(new ArrayList<Role>());
    }

    @Override
    public ResultData<Integer> findPageCount() {
        return ResultUtil.error(0);
    }

    @Override
    public ResultData<Boolean> updateRole(Role role) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<Boolean> deleteRole(String id) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<Boolean> addRole(Role role) {
        return ResultUtil.error(false);
    }

    @Override
    public ResultData<String> getMenuIdsToRoleId(String id) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }
}
