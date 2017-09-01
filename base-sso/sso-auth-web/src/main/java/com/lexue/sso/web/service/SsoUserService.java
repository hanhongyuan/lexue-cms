package com.lexue.sso.web.service;

import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.util.CookieUtil;
import com.lexue.sso.web.feignclient.SsoFeignClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lilong on 17-7-17.
 */
@Service
public class SsoUserService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SsoFeignClient ssoFeignClient;

    /**
     * 获取用户登录的tokenId
     *
     * @param request
     * @return
     */
    public String getTokenId(HttpServletRequest request) {
        return CookieUtil.getCookie(request, "tokenId");
    }

    /**
     * 根据tokenid 获取user
     *
     * @param tokenId
     * @return
     */
    public User getTokenUser(String tokenId) {
        try {
            User entity = ssoFeignClient.checkLogin(tokenId).getData();
            User user = entity;
            return user;
        } catch (Exception e) {
            logger.error("get token user error", e);
            return null;
        }
    }

    /**
     * 获取当前的用户
     *
     * @return
     */
    public User getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String tokenId = getTokenId(request);
        if (StringUtils.isNotBlank(tokenId))
            return getTokenUser(tokenId);
        else
            return null;
    }

    /**
     * 获取用户有权限的菜单
     *
     * @return
     */
    public List<Menu> getUserMenus(String userId) {
        System.out.println("userID:"+userId);
        return ssoFeignClient.getUserMenus(userId).getData();
    }

   
}
