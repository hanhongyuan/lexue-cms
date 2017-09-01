package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.User;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.UserFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class UserFeignHystrix implements UserFeignClient {
    @Override
    public ResultData addUser(User user) {
        return ResultUtil.error();
    }

    @Override
    public ResultData updateUser(User user) {
        return ResultUtil.error();
    }

    @Override
    public ResultData<List<User>> getAllUser() {
        return ResultUtil.error();
    }

    @Override
    public ResultData deleteUser(String userId) {
        return ResultUtil.error();
    }

    @Override
    public ResultData<User> getUser(String userId) {
        return ResultUtil.error();
    }

    @Override
    public ResultData<List<String>> getRids(String userId) {
        return ResultUtil.error();
    }

    @Override
    public ResultData checkLoginName(String loginName, String id) {
        return ResultUtil.error();
    }
}
