package com.lexue.sso.web.controller;

import com.lexue.base.annotation.ssoauth.NotLogin;
import com.lexue.base.domain.User;
import com.lexue.base.exception.ExceptionMessage;
import com.lexue.base.util.CookieUtil;
import com.lexue.base.util.ResultData;
import com.lexue.sso.web.feignclient.SsoFeignClient;
import com.lexue.sso.web.service.SsoUserService;
import com.lexue.sso.web.webconfig.WebConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lilong on 17-7-14.
 */
@NotLogin
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SsoFeignClient ssoFeignClient;

    @Autowired
    WebConfig webConfig;
    @Autowired
    private SsoUserService ssoUserService;

    @RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
    public String login(Model model, @RequestParam(required = false) String from, HttpServletRequest request,
                        HttpServletResponse response) {
        model.addAttribute("productName",webConfig.getName());
        try{
            User user = ssoUserService.getUser();
            if (user != null) {
                String redirect;
                if (StringUtils.isBlank(from))
                    redirect = "redirect:/main";
                else
                    redirect = "redirect:" + from;
                logger.info("目前已经登录：{}", redirect);
                return redirect;
            }
            model.addAttribute("from", from);
        }catch (Exception e){
            try {
                logger.error("Error message", e);
                ExceptionMessage ex = ExceptionMessage.parse(e);
                model.addAttribute("message", ex.getMessage());
            } catch (Exception ee) {
                if(ee instanceof com.netflix.client.ClientException){
                    logger.error("Error message", "调用远程登录服务异常");
                    model.addAttribute("message", "调用远程登录服务异常");
                }

            }
        }

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String dologin(Model model, HttpServletRequest request, HttpServletResponse response,
                          @RequestParam String username, @RequestParam String password, @RequestParam(required = false) String from) {
        logger.info("username is :{}", username);
        logger.info("password is :{}", password);
        model.addAttribute("username", username);
        try {
            ResultData<User> resultData=ssoFeignClient.userLogin(username, password);
            logger.info("resultData is {}"+resultData.toString());
            String tokenId ;
            if(resultData.getCode()==200){
                //Object user=(Object) resultData.getData();
                tokenId=resultData.getData().getTokenId();
                // 设置登录有效时间是1天
                CookieUtil.setCookie(request, response, "tokenId", tokenId, 86400);
                request.getSession().setAttribute("loginUser",resultData.getData());
                String redirect;
                if (StringUtils.isBlank(from))
                    redirect = "redirect:/main";
                else
                    redirect = "redirect:" + from;
                logger.info("登陆后跳转：{}", redirect);
                return redirect;
            }else{
                logger.info("message:"+resultData.getMessage());
                model.addAttribute("message", resultData.getMessage());
                return login(model, from, request, response);
            }

        } catch (Exception e) {

            try {
                logger.error("", e);
                ExceptionMessage ex = ExceptionMessage.parse(e);
                model.addAttribute("message", ex.getMessage());
            } catch (Exception ee) {
                logger.error("", e);
                model.addAttribute("message", "登录异常");
            }

        }
        return login(model, from, request, response);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String tokenId = ssoUserService.getTokenId(request);
        ssoFeignClient.logout(tokenId);
        CookieUtil.setCookie(request,response,"tokenId",null);
        return "redirect:/login";
    }
    /**
     * error
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(HttpServletRequest request) {
        return "error/500";
    }
    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public String error4(HttpServletRequest request) {
        return "error/401";
    }
}
