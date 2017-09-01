package com.lexue.sso.web.feignclient.fallback;

import com.lexue.base.domain.Menu;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.ResultUtil;
import com.lexue.sso.web.feignclient.MenuFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-31.
 */
@Component
public class MenuFeignHystrix implements MenuFeignClient {
    @Override
    public ResultData<List<Menu>> getAllMenu() {
        return ResultUtil.error(new ArrayList<Menu>());
    }

    @Override
    public ResultData updateMenu(Menu menu) {
        return ResultUtil.error();
    }

    @Override
    public ResultData addMenu(Menu menu) {
        return ResultUtil.error();
    }

    @Override
    public ResultData deleteMenu(String id) {
        return ResultUtil.error();
    }

    @Override
    public ResultData<Menu> getMenu(String id) {
        return ResultUtil.error(new Menu());
    }

    @Override
    public ResultData<List<Menu>> getAllMenuToRoleId(String roleId) {
        return ResultUtil.error(new ArrayList<Menu>());
    }
}
