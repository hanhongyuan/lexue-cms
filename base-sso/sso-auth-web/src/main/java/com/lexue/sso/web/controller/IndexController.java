package com.lexue.sso.web.controller;

import com.alibaba.fastjson.JSON;
import com.lexue.base.domain.Menu;
import com.lexue.base.domain.User;
import com.lexue.base.util.MenuTree;
import com.lexue.sso.web.service.SsoUserService;
import com.lexue.sso.web.webconfig.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilong on 17-7-17.
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static String [] iconList=new String[]{"fa-cubes","fa-cogs","fa-stop-circle","fa-address-book","fa-stop-circle","fa-navicon","fa-table","fa-check-square-o","fa-stop-circle","fa-address-book","fa-stop-circle","fa-navicon","fa-table","fa-check-square-o"};
    private static String [] iconChind=new String[]{"&#xe641;","&#xe63c;","&#xe609;","&#xe60c;","&#xe62a;","&#xe628;","&#xe611;","&#xe63c;","&#xe609;","&#xe60c;","&#xe62a;","&#xe628;","&#xe611;"};
    @Autowired
    private SsoUserService ssoUserService;

    @Autowired
    WebConfig webConfig;
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model, User user, HttpServletRequest request) {
        model.addAttribute("productName",webConfig.getName());
        String tokenId=ssoUserService.getTokenId(request);
        if(tokenId==null) return "redirect:/logout";
        logger.info("登录用户的tokenId is {}", tokenId);
        user=ssoUserService.getTokenUser(tokenId);
        if(user==null||user.getId()==null){
            return "redirect:/logout";
        }
        model.addAttribute("user",user);
        logger.info("user is : {}", user.getId());
        List<Menu> menuList = ssoUserService.getUserMenus(user.getId());
        logger.info("menuList is : {}", menuList);
        model.addAttribute("menuList",menuList);
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
                i++;
            }
        }
        return "index";
    }
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request) {
        return "welcome";
    }
    @RequestMapping("/menuData")
    @ResponseBody
    public String menuData(HttpServletRequest request){
        String tokenId=ssoUserService.getTokenId(request);
        User user=ssoUserService.getTokenUser(tokenId);
        List<Menu> menuList = ssoUserService.getUserMenus(user.getId());
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

}
