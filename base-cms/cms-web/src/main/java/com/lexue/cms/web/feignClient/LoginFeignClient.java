package com.lexue.cms.web.feignClient;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.util.ResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lilong on 17-7-28.
 */
@FeignClient(name = "${spring.sso-auth-name}")
public interface LoginFeignClient {

    @GetMapping("/sso/checklogin")
    User checkLogin(@RequestHeader("tokenId") String tokenId);
    @RequestMapping(value = "/sso/userLogin",method = RequestMethod.GET)
    ResultData<User> userLogin(@RequestHeader("username") String username, @RequestHeader("password") String password);
    @GetMapping("/sso/logout")
    void logout(@RequestHeader("tokenId") String tokenId);
    @GetMapping("/sso/menus")
    List<Menu> getUserMenus(@RequestHeader("userId") String userId);

}
