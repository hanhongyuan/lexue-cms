package com.lexue.cms.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.util.CookieUtil;
import com.lexue.base.util.MenuTree;
import com.lexue.base.util.ResultData;
import com.lexue.cms.web.feignClient.LoginFeignClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-28.
 */
@Controller
public class IndexController {
    private static String [] iconList=new String[]{"fa-cubes","fa-cogs","fa-stop-circle","fa-address-book","fa-stop-circle","fa-navicon","fa-table","fa-check-square-o","fa-stop-circle","fa-address-book","fa-stop-circle","fa-navicon","fa-table","fa-check-square-o"};
    private static String [] iconChind=new String[]{"&#xe641;","&#xe63c;","&#xe609;","&#xe60c;","&#xe62a;","&#xe628;","&#xe611;","&#xe63c;","&#xe609;","&#xe60c;","&#xe62a;","&#xe628;","&#xe611;"};

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    LoginFeignClient loginFeignClient;

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String url=attributes.getRequest().getRequestURI();
        String tokenId= CookieUtil.getCookie(request,"tokenId");
        if (StringUtils.isNotBlank(tokenId)){
            User user=loginFeignClient.checkLogin(tokenId);
            if(user!=null){
                logger.info("目前已经登录：用户ID {}", user.getId());
                model.addAttribute("user",user);
                return "index";
            }else{
                String loginUrl= restTemplate.getForObject("http://sso-auth-web/login",String.class);
                model.addAttribute("loginForm",loginUrl);
            }
        }else{
            String loginUrl= restTemplate.getForObject("http://sso-auth-web/login",String.class);
            model.addAttribute("loginForm",loginUrl);
        }
        model.addAttribute("form",url);
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String dologin(Model model, HttpServletRequest request, HttpServletResponse response,
                          @RequestParam String username, @RequestParam String password, @RequestParam(required = false) String from) {
        ResultData<User> data= loginFeignClient.userLogin(username,password);
        if(data.getCode()==200){
            CookieUtil.setCookie(request, response, "tokenId",data.getData().getTokenId(), 86400);
            request.getSession().setAttribute("loginUser",data.getData());
            model.addAttribute("user",data.getData());
            logger.info("目前已经登录：用户TokenId {}", data.getData().getTokenId());
            return "index";
        }else {
            model.addAttribute("message",data.getMessage());
            return index(request,response,model);
        }
    }
    @RequestMapping("/menuData")
    @ResponseBody
    public String menuData(HttpServletRequest request){
        User user=(User) request.getAttribute("loginUser");
        if(user==null){
            String tokenId= CookieUtil.getCookie(request,"tokenId");
            user=loginFeignClient.checkLogin(tokenId);
        }
        List<Menu> menuList = loginFeignClient.getUserMenus(user.getId());
        List<MenuTree> list=new ArrayList<>();
        int i=0,j=0;
        if(menuList!=null&&menuList.size()>0){
            for(Menu menu:menuList){
                if(i>=iconList.length)
                    i=0;
                MenuTree menuTree=new MenuTree();
                menuTree.setSpread(true);
                menuTree.setIcon(iconList[i]);
                menuTree.setTitle(menu.getName());
                List<MenuTree> chind=new ArrayList<>();
                if(menu.getChindMenu()!=null&&menu.getChindMenu().size()>0){
                    for(Menu me:menu.getChindMenu()){
                        if(j>=iconChind.length) j=0;
                        MenuTree menuchind=new MenuTree();
                        menuchind.setIcon(iconChind[j]);
                        menuchind.setTitle(me.getName());
                        menuchind.setHref(me.getHref());
                        chind.add(menuchind);
                        j++;
                    }
                }
                menuTree.setChildren(chind);
                list.add(menuTree);
                i++;
            }
        }
        return JSON.toJSONString(list);
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        String tokenId= CookieUtil.getCookie(request,"tokenId");
        loginFeignClient.logout(tokenId);
        CookieUtil.setCookie(request,response,"tokenId",null);
        return "redirect:/index";
    }
}
