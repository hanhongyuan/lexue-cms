package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.util.CodeEnum;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.SsoFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class SsoFeignHystrix implements SsoFeignClient {

    @Override
    public ResultData logout(String tokenId) {
        return ResultUtil.error(CodeEnum.SYS_ERROR_RPC_SERVICE);
    }

    @Override
    public ResultData<User> checkLogin(String tokenId) {
        return ResultUtil.error(new User());
    }

    @Override
    public ResultData<User> userLogin(String username, String password) {
        return ResultUtil.error(new User());
    }

    @Override
    public ResultData<List<Menu>> getUserMenus(String userId) {
        return ResultUtil.error(new ArrayList<Menu>());
    }

    @Override
    public ResultData<List<String>> getPermissionByCache(String userId) {
        return ResultUtil.error(new ArrayList<String>());
    }
}
