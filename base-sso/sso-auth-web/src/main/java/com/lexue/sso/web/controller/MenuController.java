package com.lexue.sso.web.controller;

import com.lexue.base.annotation.SystemControllerLog;
import com.lexue.base.annotation.ssoauth.Permission;
import com.lexue.base.domain.Menu;
import com.lexue.base.util.ResultData;
import com.lexue.base.util.TreeResp;
import com.lexue.base.util.ZTreeResp;
import com.lexue.sso.web.feignclient.MenuFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

import static com.lexue.base.util.ResultUtil.error;
import static com.lexue.base.util.ResultUtil.success;

/**
 * Created by lilong on 17-7-21.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MenuFeignClient menuFeignClient;
    @Permission("sys:menu:list")
    @SystemControllerLog(module = "菜单管理",method = "菜单列表")
    @RequestMapping(value = {"","/list","/"},method = RequestMethod.GET)
    public String index(Model model){
        logger.info("menucontroller init data");
        List<Menu> list= menuFeignClient.getAllMenu().getData();
        model.addAttribute("list",list);
        logger.info("menu list is {}"+list.toString());
        return "menu/list";
    }
    @RequestMapping(value = "/getTreeFrom",method = RequestMethod.GET)
    public String getTreeForm(){
        logger.info("getTreeFrom init ");
        return "/menu/tree";
    }
    @Permission("sys:menu:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String id,Model model){
        logger.info("menu add init ");
        if(id!=null){
            model.addAttribute("menu",menuFeignClient.getMenu(id).getData());
        }else
            model.addAttribute("menu",new Menu());
        return "/menu/add";
    }
    @RequestMapping(value = "/getTree",method = RequestMethod.POST)
    @ResponseBody
    public List<TreeResp> getTree(){
        logger.info("menu get tree init ");
        List<TreeResp> treeResps = new ArrayList<TreeResp>();
        treeResps.add(TreeResp.getTreeResp(menuFeignClient.getAllMenu().getData()));
        logger.info("menu get tree data is "+treeResps.toString());
        return treeResps;
    }
    @Permission("sys:menu:save")
    @SystemControllerLog(module = "菜单管理",method = "新增修改菜单")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(Menu menu){
        logger.info("menu save start");
        if(menu.getId()==null||"".equals(menu.getId())){
            menu.setId(UUID.randomUUID().toString().replace("-",""));
            menu.setUpdateDate(new Date());
            menu.setCreateDate(new Date());
            menu.setDelFlag("0");
            menuFeignClient.addMenu(menu);
        }else{
            menuFeignClient.updateMenu(menu);
        }
        return "success";
    }
    @Permission("sys:menu:delete")
    @SystemControllerLog(module = "菜单管理",method = "删除菜单")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete(String id){
        logger.info("menu delete id is {}"+id);
        menuFeignClient.deleteMenu(id);
    }
    @ResponseBody
    @PostMapping("getZTree")
    public ResultData<Object> getZTree(String roleId){
        try {
            //获取所有的有效菜单
            List<ZTreeResp> ztree=new ArrayList<>();
            List<Menu> sysMenus = menuFeignClient.getAllMenu().getData();
            //排序
            if(sysMenus!=null&&sysMenus.size()>0){
                for(Menu menu:sysMenus){
                    ZTreeResp zTreeResp=new ZTreeResp();
                    zTreeResp.setId(menu.getId());
                    zTreeResp.setName(menu.getName());
                    zTreeResp.setpId(menu.getParentId());
                    zTreeResp.setOpen(true);
                    ztree.add(zTreeResp);
                    if(menu.getChindMenu()!=null&&menu.getChindMenu().size()>0){
                        for(Menu me:menu.getChindMenu()){
                            zTreeResp=new ZTreeResp();
                            zTreeResp.setOpen(true);
                            zTreeResp.setpId(me.getParentId());
                            zTreeResp.setName(me.getName());
                            zTreeResp.setId(me.getId());
                            ztree.add(zTreeResp);
                            if(me.getChindMenu()!=null&&me.getChindMenu().size()>0){
                                for(Menu ms:me.getChindMenu()){
                                    zTreeResp=new ZTreeResp();
                                    zTreeResp.setOpen(true);
                                    zTreeResp.setpId(ms.getParentId());
                                    zTreeResp.setName(ms.getName());
                                    zTreeResp.setId(ms.getId());
                                    ztree.add(zTreeResp);
                                }
                            }
                        }
                    }
                }
            }
            List<String> checkedIds = new ArrayList<String>();
            if(roleId != null&&!"".equals(roleId)){
                List<Menu> checkedMenus = menuFeignClient.getAllMenuToRoleId(roleId).getData();
               for(ZTreeResp tree:ztree){
                   for(Menu menu:checkedMenus){
                       if(menu.getId().equals(tree.getId())){
                           tree.setChecked(true);
                       }
                   }
               }
            }
            return success(ztree);
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }
}
